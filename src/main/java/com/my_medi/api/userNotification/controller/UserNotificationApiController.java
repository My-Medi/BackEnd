package com.my_medi.api.userNotification.controller;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.*;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.service.UserNotificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[사용자 페이지]사용자 알림 API")
@RestController
@RequestMapping("/api/v1/users/notifications")
@RequiredArgsConstructor
public class UserNotificationApiController {
    private final UserNotificationQueryService userNotificationQueryService;

    @Operation(summary = "사용자의 알림을 조회합니다.")
    @GetMapping
    public ApiResponseDto<List<UserNotificationDto>> getUserNotification(@AuthUser User user) {
        List<UserNotification> notificationList = userNotificationQueryService
                .getNotificationByUserId(user.getId());

        return ApiResponseDto.onSuccess(UserNotificationConverter.toUserNotificationListDto(notificationList));
    }
}
