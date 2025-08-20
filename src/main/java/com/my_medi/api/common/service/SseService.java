package com.my_medi.api.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {

    SseEmitter connectUser(Long userId);

    SseEmitter connectExpert(Long expertId);

    void sendToUser(Long userId, Object notification);

    void sendToExpert(Long expertId, Object notification);
}
