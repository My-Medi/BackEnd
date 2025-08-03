package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.dto.ApiModificationRequest;
import com.my_medi.infra.discord.dto.DiscordWebhookMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordWebhookServiceImpl implements DiscordWebhookService{

    private final DiscordClient discordClient;
    @Override
    public void sendApiModificationRequest(String apiEndpoint, ApiModificationRequest request) {
        DiscordWebhookMessage message = createWebhookMessage(apiEndpoint, request);
        discordClient.sendAlarm(message);
    }
}
