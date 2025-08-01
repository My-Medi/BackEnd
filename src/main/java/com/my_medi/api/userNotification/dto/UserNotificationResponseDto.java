package com.my_medi.api.userNotification.dto;

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
        private Boolean isRead;
    }
}
