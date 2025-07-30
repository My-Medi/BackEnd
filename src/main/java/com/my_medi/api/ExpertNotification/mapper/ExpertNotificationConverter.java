package com.my_medi.api.ExpertNotification.mapper;

import com.my_medi.api.ExpertNotification.dto.ExpertNotificationResponseDto.*;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;

import java.util.List;
import java.util.stream.Collectors;

public class ExpertNotificationConverter {
    public static ExpertNotificationDto fromExpertNotification(ExpertNotification expertNotification) {
        ExpertNotificationDto dto = new ExpertNotificationDto();
        dto.setId(expertNotification.getId());
        dto.setExpertId(expertNotification.getExpert().getId());
        dto.setNotificationContent(expertNotification.getNotificationContent());
        dto.setSourceId(expertNotification.getSourceId());
        return dto;
    }

    public static List<ExpertNotificationDto> toExpertNotificationListDto(List<ExpertNotification> notificationList) {
        return notificationList.stream()
                .map(ExpertNotificationConverter::fromExpertNotification)
                .collect(Collectors.toList());
    }
}
