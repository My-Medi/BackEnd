package com.my_medi.api.userNotification.controller;

import com.my_medi.api.userNotification.dto.UserNotificationResponseDto.UserNotificationDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.userNotification.service.UserNotificationUseCase;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.notification.service.UserNotificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[사용자 페이지]사용자 알림 API")
@RestController
@RequestMapping("/api/v1/users/notifications")
@RequiredArgsConstructor
public class UserNotificationApiController {
    private final UserNotificationUseCase userNotificationUseCase;

    @Operation(summary = "사용자의 알림을 pagination으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<Slice<UserNotificationDto>> getUserNotificationByPage(
            @AuthUser User user,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ApiResponseDto.onSuccess(userNotificationUseCase
                .getPrioritizedNotificationDtoSliceByUserId(user.getId(), page, size));
    }
}
