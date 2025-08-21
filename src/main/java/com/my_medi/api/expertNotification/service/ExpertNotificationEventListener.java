package com.my_medi.api.expertNotification.service;

import com.my_medi.api.common.dto.NotificationEventDto;
import com.my_medi.api.common.dto.NotificationEventDto.ExpertNotificationEventDto;
import com.my_medi.api.common.service.SseService;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto;
import com.my_medi.api.expertNotification.mapper.ExpertNotificationConverter;
import com.my_medi.domain.notification.entity.ExpertNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.*;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;


@Component
@RequiredArgsConstructor
public class ExpertNotificationEventListener {

    private final SseService sseService;

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT, fallbackExecution=true)
    public void handleSendingNotificationEvent(ExpertNotificationEventDto expertNotification) {
        sseService.sendToExpert(
                expertNotification.getUsername(),
                expertNotification
        );
    }

}
