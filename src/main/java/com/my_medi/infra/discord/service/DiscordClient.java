package com.my_medi.infra.discord.service;

import com.my_medi.infra.discord.config.DiscordFeignConfig;
import com.my_medi.infra.discord.dto.DiscordWebhookMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discord-client",
        url = "${discord.webhook.url}",
        configuration = DiscordFeignConfig.class)
public interface DiscordClient {

    @PostMapping
    void sendAlarm(@RequestBody DiscordWebhookMessage message);

}
