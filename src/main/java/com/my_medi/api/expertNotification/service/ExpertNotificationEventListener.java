package com.my_medi.api.expertNotification.service;

import com.my_medi.api.common.dto.NotificationEventDto;
import com.my_medi.api.common.dto.NotificationEventDto.ExpertNotificationEventDto;
import com.my_medi.api.common.service.SseService;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto;
import com.my_medi.api.expertNotification.mapper.ExpertNotificationConverter;
import com.my_medi.domain.notification.entity.ExpertNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.*;


@Component
@RequiredArgsConstructor
public class ExpertNotificationEventListener {

    private final SseService sseService;

    public void handleSendingNotificationEvent(ExpertNotificationEventDto expertNotification) {
        sseService.sendToExpert(
                expertNotification.getExpertNotificationDto().getExpertId(),
                expertNotification
        );
    }

}
