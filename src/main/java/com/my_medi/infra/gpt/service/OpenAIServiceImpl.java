package com.my_medi.infra.gpt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.dto.OpenAIRequest;
import com.my_medi.infra.gpt.dto.OpenAIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.my_medi.common.consts.StaticVariable.*;
import static com.my_medi.common.util.ParseUtil.*;
import static com.my_medi.common.util.PromptUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Environment environment;

    @Override
    public HealthReportData parseHealthReport(String imageBase64) {
        //GET ENV value
        String openAiApiKey = environment.getProperty("openai.api.key");
        String openAiApiUrl = environment.getProperty("openai.api.url");
        //EXTRACT by prompt
        OpenAIRequest openAIRequest = extractTextFromImage(imageBase64);
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
                    .checkupDate(parseDate(jsonNode.path(CHECKUP_DATE).asText()))
                    .round(jsonNode.path(ROUND).asInt())
                    .measurement(parseMeasurement(jsonNode.path(MEASUREMENT)))
                    .bloodPressure(parseBloodPressure(jsonNode.path(BLOOD_PRESSURE)))
                    .bloodTest(parseBloodTest(jsonNode.path(BLOOD_TEST)))
                    .urineTest(parseUrineTest(jsonNode.path(URINE_TEST)))
                    .imagingTest(parseImagingTest(jsonNode.path(IMAGING_TEST)))
                    .interview(parseInterview(jsonNode.path(INTERVIEW)))
                    .additionalTest(parseAdditionalTest(jsonNode.path(ADDITIONAL_TEST)))
                    .build();
        } catch (Exception e) {
            //TODO exception 처리
            log.error("JSON 파싱 중 오류 발생: {}", jsonResponse, e);
            throw new RuntimeException("추출된 데이터 파싱에 실패했습니다.", e);
        }
    }

    @Override
    public String ask(String prompt) {

        OpenAIRequest req = OpenAIRequest.fromUserText(
                environment.getProperty("openai.api.model", "gpt-4o-mini"),
                prompt, 500, 0.2);

        return sendAndExtractContent(req);
    }

    // 요청 객체를 받아 실제 호출하고 content만 뽑는 공통 메서드
    private String sendAndExtractContent(OpenAIRequest req) {
        String openAiApiKey = environment.getProperty("openai.api.key");
        String openAiApiUrl = environment.getProperty("openai.api.url");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);
        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(req, headers);

        ResponseEntity<OpenAIResponse> response =
                restTemplate.exchange(openAiApiUrl, HttpMethod.POST, entity, OpenAIResponse.class);

        return response.getBody()
                .getChoices().get(0)
                .getMessage()
                .getContent();
    }


}
