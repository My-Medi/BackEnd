package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.dto.ApiModificationRequest;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.http.HttpMethod;

public interface DiscordWebhookService {
    void sendApiModificationRequest(String apiEndpoint, PathItem.HttpMethod httpMethod, ApiModificationRequest request);
}
