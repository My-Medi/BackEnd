package com.my_medi.api.userNotification.mapper;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;
import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserNotificationConverter {
    public static UserNotificationDto fromUserNotification(UserNotification userNotification) {
        UserNotificationDto dto = new UserNotificationDto();
        dto.setId(userNotification.getId());
        dto.setUserId(userNotification.getUser().getId());
        dto.setNotificationContent(userNotification.getNotificationContent());
        dto.setSourceId(userNotification.getSourceId());
        dto.setIsRead(userNotification.getIsRead());
        return dto;
    }

    public static Page<UserNotificationDto> toUserNotificationPageDto(Page<UserNotification> notificationPage) {
        return notificationPage.map(UserNotificationConverter::fromUserNotification);
    }
}
