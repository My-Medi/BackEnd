package com.my_medi.api.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
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
    public SseEmitter connectUser(String userUsername) {
        String key = "user_" + userUsername;
        SseEmitter emitter = new SseEmitter(10000 * 60 * 60L);

        emitter.onCompletion(() -> userEmitters.remove(key));
        emitter.onTimeout(() -> userEmitters.remove(key));
        emitter.onError((e) -> {
            log.error("SSE error for user {}: {}", userUsername, e.getMessage());
            userEmitters.remove(key);
        });

        userEmitters.put(key, emitter);

        try {
            // 연결 확인 메시지
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결이 성공했습니다"));
        } catch (IOException e) {
            log.error("Failed to send connection message to user {}", userUsername, e);
            userEmitters.remove(key);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Override
    public SseEmitter connectExpert(String expertUsername) {
        String key = "expert_" + expertUsername;
        SseEmitter emitter = new SseEmitter(10000 * 60 * 60L);

        emitter.onCompletion(() -> expertEmitters.remove(key));
        emitter.onTimeout(() -> expertEmitters.remove(key));
        emitter.onError((e) -> {
            log.error("SSE error for expert {}: {}", expertUsername, e.getMessage());
            expertEmitters.remove(key);
        });

        expertEmitters.put(key, emitter);

        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("SSE 연결이 성공했습니다"));
        } catch (IOException e) {
            log.error("Failed to send connection message to expert {}", expertUsername, e);
            expertEmitters.remove(key);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Override
    public void sendToUser(String userUsername, Object notification) {
        String key = "user_" + userUsername;
        SseEmitter emitter = userEmitters.get(key);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(notification));
            } catch (IOException e) {
                log.error("Failed to send notification to user {}", userUsername, e);
                userEmitters.remove(key);
            }
        }
    }

    @Override
    public void sendToExpert(String expertUsername, Object notification) {
        String key = "expert_" + expertUsername;
        SseEmitter emitter = expertEmitters.get(key);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("notification")
                        .data(notification));
            } catch (IOException e) {
                log.error("Failed to send notification to expert {}", expertUsername, e);
                expertEmitters.remove(key);
            }
        }
    }
    @Scheduled(fixedRate = 30000)
    public void sendHeartbeatToUser() {
        userEmitters.forEach((key, emitter) -> {
            try {
                // 주석(comment)을 보내면 클라이언트에서 별도의 이벤트를 발생시키지 않음
                emitter.send(SseEmitter.event().comment("heartbeat"));
            } catch (IOException e) {
                // IOException 발생 시 연결이 끊어졌다고 판단하고 맵에서 제거
                userEmitters.remove(key);
            }
        });
    }
    @Scheduled(fixedRate = 30000)
    public void sendHeartbeatToExpert() {
        expertEmitters.forEach((key, emitter) -> {
            try {
                // 주석(comment)을 보내면 클라이언트에서 별도의 이벤트를 발생시키지 않음
                emitter.send(SseEmitter.event().comment("heartbeat"));
            } catch (IOException e) {
                // IOException 발생 시 연결이 끊어졌다고 판단하고 맵에서 제거
                expertEmitters.remove(key);
            }
        });
    }
}
