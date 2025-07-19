package com.my_medi.infra.gpt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.dto.OpenAIRequest;
import com.my_medi.infra.gpt.dto.OpenAIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.my_medi.common.util.ParseUtil.*;
import static com.my_medi.common.util.PromptUtil.extractTextFromImage;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService{

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Environment environment;

    @Override
    public HealthReportData parseHealthReport(String imageBase64) {
        //GET ENV value
        String openAiApiKey = environment.getProperty("");
        String openAiApiUrl = environment.getProperty("");
        //EXTRACT by prompt
        OpenAIRequest openAIRequest = extractTextFromImage(imageBase64, openAiApiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);
        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);
        ResponseEntity<OpenAIResponse> response = restTemplate.exchange(
                openAiApiUrl, HttpMethod.POST, entity, OpenAIResponse.class);

        return parseExtractedData(response.getBody()
                .getChoices().get(0)
                .getMessage()
                .getContent());
    }

    private HealthReportData parseExtractedData(String jsonResponse) {
        try {
            // JSON에서 실제 JSON 부분만 추출
            int startIndex = jsonResponse.indexOf("{");
            int endIndex = jsonResponse.lastIndexOf("}") + 1;
            String jsonPart = jsonResponse.substring(startIndex, endIndex);

            JsonNode jsonNode = objectMapper.readTree(jsonPart);

            return HealthReportData.builder()
                    .checkupDate(parseDate(jsonNode.path("checkupDate").asText()))
                    .round(jsonNode.path("round").asInt())
                    .measurement(parseMeasurement(jsonNode.path("measurement")))
                    .bloodPressure(parseBloodPressure(jsonNode.path("bloodPressure")))
                    .bloodTest(parseBloodTest(jsonNode.path("bloodTest")))
                    .urineTest(parseUrineTest(jsonNode.path("urineTest")))
                    .imagingTest(parseImagingTest(jsonNode.path("imagingTest")))
                    .interview(parseInterview(jsonNode.path("interview")))
                    .additionalTest(parseAdditionalTest(jsonNode.path("additionalTest")))
                    .build();
        } catch (Exception e) {
            //TODO exception 처리
            log.error("JSON 파싱 중 오류 발생: {}", jsonResponse, e);
            throw new RuntimeException("추출된 데이터 파싱에 실패했습니다.", e);
        }
    }
}
