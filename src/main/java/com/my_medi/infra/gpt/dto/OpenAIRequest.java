package com.my_medi.infra.gpt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenAIRequest {
    private String model;
    private List<Message> messages;
    @JsonProperty("max_tokens")
    private int maxTokens;
    private double temperature;

    @Data
    @Builder
    public static class Message {
        private String role;
        private List<Content> content;
        private String text;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Content {
        private String type;
        private String text;
        @JsonProperty("image_url")
        private ImageUrl imageUrl;
    }

    @Data
    @Builder
    public static class ImageUrl {
        private String url;
    }

    // 📌 헬퍼 메서드
    public static OpenAIRequest fromUserText(String model, String text, int maxTokens, double temperature) {
        return OpenAIRequest.builder()
                .model(model)
                .maxTokens(maxTokens)
                .temperature(temperature)
                .messages(List.of(
                        Message.builder()
                                .role("user")
                                .content(List.of(
                                        Content.builder()
                                                .type("text")
                                                .text(text)
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }
}