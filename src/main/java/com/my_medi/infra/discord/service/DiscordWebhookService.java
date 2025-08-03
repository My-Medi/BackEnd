package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.dto.ApiModificationRequest;

public interface DiscordWebhookService {
    void sendApiModificationRequest(String apiEndpoint, ApiModificationRequest request);
}
