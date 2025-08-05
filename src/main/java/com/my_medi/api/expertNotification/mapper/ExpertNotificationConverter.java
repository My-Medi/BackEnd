package com.my_medi.api.expertNotification.mapper;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationSimplePageResponse;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;

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

    public static ExpertNotificationSimplePageResponse toExpertNotificationPageDto(Page<ExpertNotification> notificationPage) {
        List<ExpertNotificationDto> content = notificationPage.getContent().stream()
                .map(ExpertNotificationConverter::fromExpertNotification)
                .collect(Collectors.toList());

        return new ExpertNotificationSimplePageResponse(
                content,
                notificationPage.getTotalPages(),
                notificationPage.getNumber()
        );
    }
}
