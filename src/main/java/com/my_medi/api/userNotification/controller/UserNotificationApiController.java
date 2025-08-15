package com.my_medi.api.userNotification.controller;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationSimplePageResponse;
import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.api.userNotification.service.UserNotificationUseCase;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.exception.UserNotificationHandler;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[사용자 페이지]사용자 알림 API")
@RestController
@RequestMapping("/api/v1/users/notifications")
@RequiredArgsConstructor
public class UserNotificationApiController {
    private final UserNotificationUseCase userNotificationUseCase;
    private final UserNotificationCommandService userNotificationCommandService;

    @Operation(summary = "사용자의 알림을 pagination 으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<UserNotificationSimplePageResponse> getUserNotificationByPage(
            @AuthUser User user,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {

        Page<UserNotification> userNotificationPage = userNotificationUseCase
                .getPrioritizedNotificationDtoPageByUserId(user.getId(), currentPage, pageSize);

        return ApiResponseDto.onSuccess(
                UserNotificationConverter.toUserNotificationPageDto(userNotificationPage)
        );
    }

    @Operation(summary = "사용자의 알림을 '읽음' 상태로 만듭니다.")
    @PatchMapping("/{notificationId}")
    public ApiResponseDto<Long> updateUserNotification(@PathVariable Long notificationId) {

        return ApiResponseDto.onSuccess(userNotificationCommandService
                .readUserNotification(notificationId));
    }

    @Operation(summary = "사용자의 알림을 삭제합니다.")
    @DeleteMapping
    public ApiResponseDto<Void> deleteUserNotifications(@RequestParam(required = false) List<Long> notificationId) {
        if (notificationId == null || notificationId.isEmpty()) {
            throw UserNotificationHandler.EMPTY_NOTIFICATION_ID_LIST;
        }

        userNotificationCommandService.removeNotifications(notificationId);
        return ApiResponseDto.onSuccess(null);
    }

}
