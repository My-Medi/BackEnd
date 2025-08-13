package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.dto.ApiModificationRequest;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
@ConditionalOnProperty(prefix = "discord.alert", name = "enabled", havingValue = "false", matchIfMissing = true)
public class DiscordWebhookServiceNoop implements DiscordWebhookService {
    @Override public void sendApiModificationRequest(String api, PathItem.HttpMethod m, ApiModificationRequest r) {}
    @Override public void sendErrorMessage(Exception e, WebRequest req) {}
}