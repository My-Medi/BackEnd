package com.my_medi.common.util;

import com.my_medi.common.consts.Prompt;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.infra.gpt.dto.OpenAIRequest;
import com.my_medi.infra.gpt.dto.OpenAIResponse;
import org.springframework.http.*;

import java.util.List;

import static com.my_medi.common.consts.Prompt.*;

public class PromptUtil {
    public static OpenAIRequest extractTextFromImage(String imageBase64) {
        String prompt = Prompt.IMAGE_PARSING_PROMPT;

        OpenAIRequest.Content textContent = OpenAIRequest.Content.builder()
                .type(TEXT)
                .text(prompt)
                .build();

        OpenAIRequest.Content imageContent = OpenAIRequest.Content.builder()
                .type(TYPE_IMAGE_URL)
                .imageUrl(
                        OpenAIRequest.ImageUrl.builder()
                                .url(IMAGE_URL_URL + imageBase64)
                                .build()
                )
                .build();

        OpenAIRequest.Message message = OpenAIRequest.Message.builder()
                .role(ROLE_USER)
                .content(List.of(textContent, imageContent))
                .build();

        return OpenAIRequest.builder()
                .model(MODEL_GPT_4O)
                .maxTokens(MAX_TOKEN)
                .temperature(TEMPERATURE)
                .messages(List.of(message))
                .build();
    }

    // ===== HealthTerm(텍스트 질의) =====
    public static OpenAIRequest buildHealthTermRequest(String term) {
        String prompt = HEALTH_TERM_PROMPT.formatted(term == null ? "" : term.trim());

        OpenAIRequest.Content textContent = OpenAIRequest.Content.builder()
                .type(TEXT)
                .text(prompt)
                .build();

        OpenAIRequest.Message message = OpenAIRequest.Message.builder()
                .role(ROLE_USER)
                .content(List.of(textContent))
                .build();

        return OpenAIRequest.builder()
                .model(MODEL_GPT_4O)      // 필요 시 yml에서 읽어오게 바꿔도 됨
                .maxTokens(500)           // HealthTerm은 500~800 토큰이면 충분
                .temperature(0.2)
                .messages(List.of(message))
                .build();
    }

    // 폴백용 텍스트 요청
    public static OpenAIRequest buildHealthTermFallbackRequest(String term) {
        String prompt = HEALTH_TERM_FALLBACK.formatted(term == null ? "" : term.trim());

        OpenAIRequest.Content textContent = OpenAIRequest.Content.builder()
                .type(TEXT)
                .text(prompt)
                .build();

        OpenAIRequest.Message message = OpenAIRequest.Message.builder()
                .role(ROLE_USER)
                .content(List.of(textContent))
                .build();

        return OpenAIRequest.builder()
                .model(MODEL_GPT_4O)
                .maxTokens(200)
                .temperature(0.2)
                .messages(List.of(message))
                .build();
    }

    public static String healthTermPrompt(String term) {
        return String.format(HEALTH_TERM_PROMPT, term == null ? "" : term.trim());
    }

    public static String healthTermFallback(String term) {
        return String.format(HEALTH_TERM_FALLBACK, term == null ? "" : term.trim());
    }
}
