package com.my_medi.api.userNotification.controller;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.userNotification.service.UserNotificationUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자 페이지]사용자 알림 API")
@RestController
@RequestMapping("/api/v1/users/notifications")
@RequiredArgsConstructor
public class UserNotificationApiController {
    private final UserNotificationUseCase userNotificationUseCase;
    private final UserNotificationCommandService userNotificationCommandService;

    @Operation(summary = "사용자의 알림을 pagination으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<Slice<UserNotificationDto>> getUserNotificationByPage(
            @AuthUser User user,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ApiResponseDto.onSuccess(userNotificationUseCase
                .getPrioritizedNotificationDtoSliceByUserId(user.getId(), page, size));
    }

    @Operation(summary = "사용자의 알림을 '읽음' 상태로 만듭니다.")
    @PatchMapping
    public ApiResponseDto<Long> updateUserNotification(@AuthUser User user,
                                                       @RequestParam Long sourceId) {

        return ApiResponseDto.onSuccess(userNotificationCommandService
                .readNotification(user.getId(), sourceId));
    }
}
