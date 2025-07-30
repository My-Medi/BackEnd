package com.my_medi.api.userNotification.dto;

import com.my_medi.domain.userNotification.entity.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserNotificationResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationDto {
        private Long id;
        private Long userId;
        private String notificationContent;
        private Long sourceId;
    }
}
