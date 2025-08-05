package com.my_medi.api.userNotification.mapper;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationSimplePageResponse;
import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserNotificationConverter {

    public static UserNotificationDto fromUserNotification(UserNotification userNotification) {
        return UserNotificationDto.builder()
                .id(userNotification.getId())
                .userId(userNotification.getUser().getId())
                .notificationContent(userNotification.getNotificationContent())
                .sourceId(userNotification.getSourceId())
                .isRead(userNotification.getIsRead())
                .build();
    }

    public static UserNotificationSimplePageResponse toUserNotificationPageDto(Page<UserNotification> notificationPage) {
        List<UserNotificationDto> content = notificationPage.getContent().stream()
                .map(UserNotificationConverter::fromUserNotification)
                .collect(Collectors.toList());

        return new UserNotificationSimplePageResponse(
                content,
                notificationPage.getTotalPages(),
                notificationPage.getNumber()
        );
    }
}
