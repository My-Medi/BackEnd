package com.my_medi.api.userNotification.mapper;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;
import com.my_medi.domain.userNotification.entity.UserNotification;

import java.util.List;
import java.util.stream.Collectors;

public class UserNotificationConverter {
    public static List<UserNotificationDto> toUserNotificationListDto(List<UserNotification> notificationList) {
        return notificationList.stream()
                .map(UserNotificationDto::new)
                .collect(Collectors.toList());
    }
}
