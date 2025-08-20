package com.my_medi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SseEmitterConfig {

    @Bean(name = "userEmitters")
    public Map<String, SseEmitter> userEmitters() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "expertEmitters")
    public Map<String, SseEmitter> expertEmitters() {
        return new ConcurrentHashMap<>();
    }
}
