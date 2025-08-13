package com.my_medi.infra.gpt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_medi.common.consts.Prompt;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.dto.OpenAIRequest;
import com.my_medi.infra.gpt.dto.OpenAIResponse;
import com.my_medi.infra.gpt.dto.TotalReportData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
    private final UserRepository userRepository;
    private final ReportQueryService reportQueryService;


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



    @Override
    public TotalReportData buildTotalReport(Long userId, Integer round) {
        try {

            var user = userRepository.findById(userId)
                    .orElseThrow(() -> ReportHandler.NOT_FOUND);

            var compareResult = Optional.ofNullable(reportQueryService.compareReport(user, round))
                    .orElseThrow(() -> new RuntimeException("해당 라운드의 비교 리포트가 존재하지 않습니다."));

            String compareJson = objectMapper.writeValueAsString(compareResult);

            String prompt = String.format(Prompt.TOTAL_REPORT_PROMPT, compareJson);

            String openAiApiKey = environment.getProperty("openai.api.key");
            String openAiApiUrl = environment.getProperty("openai.api.url");

            OpenAIRequest request = OpenAIRequest.builder()
                    .model(Prompt.MODEL_GPT_4O)
                    .messages(List.of(
                            OpenAIRequest.Message.builder()
                                    .role(Prompt.ROLE_USER)
                                    .content(List.of(
                                            OpenAIRequest.Content.builder()
                                                    .type(Prompt.TEXT)
                                                    .text(prompt)
                                                    .build()
                                    ))
                                    .build()
                    ))
                    .maxTokens(Prompt.MAX_TOKEN)
                    .temperature(Prompt.TEMPERATURE)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiApiKey);

            HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<OpenAIResponse> response = restTemplate.exchange(
                    openAiApiUrl, HttpMethod.POST, entity, OpenAIResponse.class);

            String gptContent = response.getBody()
                    .getChoices().get(0)
                    .getMessage()
                    .getContent();

            int startIndex = gptContent.indexOf("{");
            int endIndex = gptContent.lastIndexOf("}") + 1;
            String jsonPart = gptContent.substring(startIndex, endIndex);

            TotalReportData result = objectMapper.readValue(jsonPart, TotalReportData.class);

            result.setNickname(user.getNickname());

            return result;

        } catch (Exception e) {
            log.error("TotalReportData 생성 중 오류 발생", e);
            throw new RuntimeException("TotalReportData 생성 실패", e);
        }
    }


}
