package com.my_medi.api.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {

    SseEmitter connectUser(String userUsername);

    SseEmitter connectExpert(String expertUsername);

    void sendToUser(String userUsername, Object notification);

    void sendToExpert(String expertUsername, Object notification);
}
