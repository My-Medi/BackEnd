package com.my_medi.api.userNotification.service;

import com.my_medi.api.common.dto.NotificationEventDto;
import com.my_medi.api.common.dto.NotificationEventDto.UserNotificationEventDto;
import com.my_medi.api.common.service.SseService;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.domain.notification.entity.UserNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserNotificationEventListener {

    private final SseService sseService;
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT, fallbackExecution=true)
    public void handleSendingNotificationEvent(UserNotificationEventDto userNotification){
        sseService.sendToUser(
                userNotification.getUsername(),
                userNotification
        );
    }
}
