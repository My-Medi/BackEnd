package com.my_medi.api.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService{
    @Qualifier("userEmitters")
    private final Map<String, SseEmitter> userEmitters;

    @Qualifier("expertEmitters")
    private final Map<String, SseEmitter> expertEmitters;
    @Override
    public SseEmitter connectUser(Long userId) {
        String key = "user_" + userId;
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> userEmitters.remove(key));
        emitter.onTimeout(() -> userEmitters.remove(key));
        emitter.onError((e) -> {
            log.error("SSE error for user {}: {}", userId, e.getMessage());
            userEmitters.remove(key);
        });

        userEmitters.put(key, emitter);

        try {
            // 연결 확인 메시지
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결이 성공했습니다"));
        } catch (IOException e) {
            log.error("Failed to send connection message to user {}", userId, e);
            userEmitters.remove(key);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Override
    public SseEmitter connectExpert(Long expertId) {
        String key = "expert_" + expertId;
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> expertEmitters.remove(key));
        emitter.onTimeout(() -> expertEmitters.remove(key));
        emitter.onError((e) -> {
            log.error("SSE error for expert {}: {}", expertId, e.getMessage());
            expertEmitters.remove(key);
        });

        expertEmitters.put(key, emitter);

        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결이 성공했습니다"));
        } catch (IOException e) {
            log.error("Failed to send connection message to expert {}", expertId, e);
            expertEmitters.remove(key);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Override
    public void sendToUser(Long userId, Object notification) {
        String key = "user_" + userId;
        SseEmitter emitter = userEmitters.get(key);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(notification));
            } catch (IOException e) {
                log.error("Failed to send notification to user {}", userId, e);
                userEmitters.remove(key);
            }
        }
    }

    @Override
    public void sendToExpert(Long expertId, Object notification) {
        String key = "expert_" + expertId;
        SseEmitter emitter = expertEmitters.get(key);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(notification));
            } catch (IOException e) {
                log.error("Failed to send notification to expert {}", expertId, e);
                expertEmitters.remove(key);
            }
        }
    }
}
