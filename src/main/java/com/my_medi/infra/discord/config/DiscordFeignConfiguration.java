package com.my_medi.infra.discord.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@Configuration
public class DiscordFeignConfiguration {
    @Profile("deploy")
    @Bean
    Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }
    @Profile("deploy")
    @Bean
    public ErrorDecoder discordErrorDecoder() {
        return new ErrorDecoder.Default(); // 기본 동작: 상태코드 기반 예외 생성
    }
    @Profile("deploy")
    @Bean
    public RequestInterceptor jsonHeaders() {
        return tpl -> {
            tpl.header("Content-Type", "application/json; charset=UTF-8");
            tpl.header("Accept", "application/json");
        };
    }
}


