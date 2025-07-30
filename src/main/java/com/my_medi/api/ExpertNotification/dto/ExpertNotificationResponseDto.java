package com.my_medi.api.ExpertNotification.dto;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ExpertNotificationResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpertNotificationDto {
        private Long id;
        private Long expertId;
        private String notificationContent;
        private Long sourceId;
    }
}
