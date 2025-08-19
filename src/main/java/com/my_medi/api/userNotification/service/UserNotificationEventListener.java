package com.my_medi.api.userNotification.service;

import com.my_medi.api.common.service.SseService;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;

@Service
@RequiredArgsConstructor
public class UserNotificationEventListener {

    private final SseService sseService;

    @Async
    @EventListener
    public void handleConsultationApproved(Long userId, UserNotificationDto event){
        sseService.sendToUser(userId, NotificationCon);
    }

    @Async
    @EventListener
    public void handleScheduleCreated(Long userId, UserNotificationDto event){
        sseService.sendToUser(userId, event);
    }
}
