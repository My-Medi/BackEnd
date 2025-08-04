package com.my_medi.infra.discord.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscordWebhookMessage {
    private String content;
    private Embed[] embeds;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Embed {
        private String title;
        private String description;
        private int color;
        private Field[] fields;
        private Footer footer;
        private String timestamp;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Field {
            private String name;
            private String value;
            private boolean inline;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Footer {
            private String text;
        }
    }
}

