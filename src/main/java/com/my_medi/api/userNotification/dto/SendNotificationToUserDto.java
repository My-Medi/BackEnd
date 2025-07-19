package com.my_medi.api.userNotification.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendNotificationToUserDto {
    private Long sourceId;
    private String title;
    private String content;
}
