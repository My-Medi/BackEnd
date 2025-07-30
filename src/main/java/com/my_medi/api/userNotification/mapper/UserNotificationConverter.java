package com.my_medi.api.userNotification.mapper;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;
import com.my_medi.domain.userNotification.entity.UserNotification;

import java.util.List;
import java.util.stream.Collectors;

public class UserNotificationConverter {
    public static UserNotificationDto fromUserNotification(UserNotification userNotification) {
        UserNotificationDto dto = new UserNotificationDto();
        dto.setId(userNotification.getId());
        dto.setUserId(userNotification.getUser().getId());
        dto.setNotificationContent(userNotification.getNotificationContent());
        dto.setSourceId(userNotification.getSourceId());
        return dto;
    }

    public static List<UserNotificationDto> toUserNotificationListDto(List<UserNotification> notificationList) {
        return notificationList.stream()
                .map(UserNotificationConverter::fromUserNotification)
                .collect(Collectors.toList());
    }
}
