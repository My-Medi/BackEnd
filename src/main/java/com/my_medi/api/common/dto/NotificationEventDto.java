package com.my_medi.api.common.dto;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class NotificationEventDto {

    @Data
    @Builder
    @AllArgsConstructor
    public static class UserNotificationEventDto{
        private String username;
        private UserNotificationDto userNotificationDto;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class ExpertNotificationEventDto{
        private String username;
        private ExpertNotificationDto expertNotificationDto;
    }


}
