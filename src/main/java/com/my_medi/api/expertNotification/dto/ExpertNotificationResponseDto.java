package com.my_medi.api.expertNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ExpertNotificationResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpertNotificationDto {
        private Long expertNotificationId;
        private Long expertId;
        private String notificationContent;
        private Long sourceId;
        private Boolean isRead;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpertNotificationSimplePageResponse {
        private List<ExpertNotificationResponseDto.ExpertNotificationDto> content;
        private Integer totalPages;
        private Integer page;
    }
}
