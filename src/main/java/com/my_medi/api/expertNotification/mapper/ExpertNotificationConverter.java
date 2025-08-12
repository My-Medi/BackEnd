package com.my_medi.api.expertNotification.mapper;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationSimplePageResponse;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

//TODO converter method 명칭 컨벤션 통일하기 (to~)
public class ExpertNotificationConverter {
    public static ExpertNotificationDto fromExpertNotification(ExpertNotification expertNotification) {
        return ExpertNotificationDto.builder()
                .expertNotificationId(expertNotification.getId())
                .expertId(expertNotification.getExpert().getId())
                .notificationContent(expertNotification.getNotificationContent())
                .sourceId(expertNotification.getSourceId())
                .isRead(expertNotification.getIsRead())
                .build();
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
