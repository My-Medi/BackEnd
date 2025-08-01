package com.my_medi.api.expertNotification.mapper;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.domain.notification.entity.ExpertNotification;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ExpertNotificationDto> toExpertNotificationListDto(List<ExpertNotification> notificationList) {
        return notificationList.stream()
                .map(ExpertNotificationConverter::fromExpertNotification)
                .collect(Collectors.toList());
    }
}
