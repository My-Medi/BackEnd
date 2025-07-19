package com.my_medi.infra.gpt.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenAIRequest {
    private String model;
    private List<Message> messages;
    private int maxTokens;
    private double temperature;

    @Data
    @Builder
    public static class Message {
        private String role;
        private List<Content> content;
    }

    @Data
    @Builder
    public static class Content {
        private String type;
        private String text;
        private ImageUrl imageUrl;
    }

    @Data
    @Builder
    public static class ImageUrl {
        private String url;
    }
}