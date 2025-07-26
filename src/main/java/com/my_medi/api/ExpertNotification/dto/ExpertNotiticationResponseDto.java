package com.my_medi.api.ExpertNotification.dto;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ExpertNotiticationResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpertNotiticationDto {
        private Long id;
        private Long expertId;
        private String notificationContent;
        private Long sourceId;

        public ExpertNotiticationDto(ExpertNotification notification) {
            this.id = notification.getId();
            this.expertId = notification.getExpert().getId();
            this.notificationContent = notification.getNotificationContent();
            this.sourceId = notification.getSourceId();
        }
    }
}
