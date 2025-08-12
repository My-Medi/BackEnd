package com.my_medi.infra.discord.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.my_medi.infra.discord.dto.DiscordWebhookErrorMessage;
import com.my_medi.infra.discord.config.DiscordFeignConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discord-client",
        url = "${discord.webhook.error}",
        configuration = DiscordFeignConfiguration.class
)
public interface DiscordClient {
    @PostMapping
    void sendAlarm(@RequestBody DiscordWebhookErrorMessage message);
}
