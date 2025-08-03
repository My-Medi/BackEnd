package com.my_medi.api.expertNotification.mapper;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;

public class ExpertNotificationConverter {
    public static ExpertNotificationDto fromExpertNotification(ExpertNotification expertNotification) {
        ExpertNotificationDto dto = new ExpertNotificationDto();
        dto.setId(expertNotification.getId());
        dto.setExpertId(expertNotification.getExpert().getId());
        dto.setNotificationContent(expertNotification.getNotificationContent());
        dto.setSourceId(expertNotification.getSourceId());
        dto.setIsRead(expertNotification.getIsRead());
        return dto;
    }

    public static Page<ExpertNotificationDto> toExpertNotificationPageDto(Page<ExpertNotification> notificationPage) {
        return notificationPage.map(ExpertNotificationConverter::fromExpertNotification);
    }
}
