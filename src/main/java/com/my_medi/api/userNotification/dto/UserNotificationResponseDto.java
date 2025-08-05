package com.my_medi.api.userNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserNotificationResponseDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationDto {
        private Long userNotificationId;
        private Long userId;
        private String notificationContent;
        private Long sourceId;
        private Boolean isRead;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationSimplePageResponse {
        private List<UserNotificationDto> content;
        private Integer totalPages;
        private Integer page;
    }
}
