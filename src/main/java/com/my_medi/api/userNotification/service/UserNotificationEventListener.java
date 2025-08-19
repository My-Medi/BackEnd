package com.my_medi.api.userNotification.service;

import com.my_medi.api.common.dto.NotificationEventDto;
import com.my_medi.api.common.dto.NotificationEventDto.UserNotificationEventDto;
import com.my_medi.api.common.service.SseService;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.domain.notification.entity.UserNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;

@Component
@RequiredArgsConstructor
public class UserNotificationEventListener {

    private final SseService sseService;
    @Async
    @EventListener
    public void handleSendingNotificationEvent(UserNotificationEventDto userNotification){
        sseService.sendToUser(
                userNotification.getUserNotificationDto().getUserId(),
                userNotification
        );
    }
}
