// Service
package com.my_medi.infra.gpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.common.util.PromptUtil;
import com.my_medi.infra.gpt.dto.HealthTermResponse;
import com.my_medi.infra.gpt.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthTermService {

    private final OpenAIService openAIService;
    private final ObjectMapper objectMapper;

    public HealthTermResponse define(String rawTerm) {
        String term = rawTerm == null ? "" : rawTerm.trim();
        String prompt = PromptUtil.healthTermPrompt(term);

        try {
            // GPT content에서 JSON만 추출
            String content = openAIService.ask(prompt);
            int s = content.indexOf("{");
            int e = content.lastIndexOf("}") + 1;
            String json = (s >= 0 && e > s) ? content.substring(s, e) : content;

            HealthTermResponse dto = objectMapper.readValue(json, HealthTermResponse.class);

            if (dto.getTerm() == null || dto.getTerm().isBlank()) dto.setTerm(term);
            return dto;

        } catch (Exception ex) {
            String fallback = openAIService.ask(PromptUtil.healthTermFallback(term));
            return HealthTermResponse.builder()
                    .term(term)
                    .shortDesc(fallback)
                    .description(fallback)
                    .normalRange(null)
                    .build();
        }
    }
}
