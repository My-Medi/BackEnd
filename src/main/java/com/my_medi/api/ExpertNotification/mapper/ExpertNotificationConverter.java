package com.my_medi.api.ExpertNotification.mapper;

import com.my_medi.api.ExpertNotification.dto.ExpertNotiticationResponseDto.*;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;

import java.util.List;
import java.util.stream.Collectors;

public class ExpertNotificationConverter {
    public static List<ExpertNotiticationDto> toExpertNotiticationListDto(List<ExpertNotification> notificationList) {
        return notificationList.stream()
                .map(ExpertNotiticationDto::new)
                .collect(Collectors.toList());
    }
}
