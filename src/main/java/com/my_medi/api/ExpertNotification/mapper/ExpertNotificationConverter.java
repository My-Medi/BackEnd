package com.my_medi.api.ExpertNotification.mapper;

import com.my_medi.api.ExpertNotification.dto.ExpertNotificationResponseDto.*;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;

import java.util.List;
import java.util.stream.Collectors;

public class ExpertNotificationConverter {
    public static List<ExpertNotificationDto> toExpertNotificationListDto(List<ExpertNotification> notificationList) {
        return notificationList.stream()
                .map(ExpertNotificationDto::new)
                .collect(Collectors.toList());
    }
}
