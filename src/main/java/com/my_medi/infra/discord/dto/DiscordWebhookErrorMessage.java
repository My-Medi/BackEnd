package com.my_medi.infra.discord.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DiscordWebhookErrorMessage {
    private String content;
    private List<Embed> embeds;

    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Embed {
        private String title;
        private String description;
    }
}
