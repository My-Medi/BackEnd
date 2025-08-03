package com.my_medi.api.expertNotification.controller;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.expertNotification.service.ExpertNotificationUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지]전문가 알림 API")
@RestController
@RequestMapping("/api/v1/experts/notifications")
@RequiredArgsConstructor
public class ExpertNotificationApiController {
    private final ExpertNotificationUseCase expertNotificationUseCase;
    private final ExpertNotificationCommandService expertNotificationCommandService;

    @Operation(summary = "전문가의 알림을 paginatin으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<Slice<ExpertNotificationDto>> getExpertNotification(
            @AuthExpert Expert expert,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ApiResponseDto.onSuccess(expertNotificationUseCase
                .getPrioritizedNotificationDtoSliceByExpertId(expert.getId(), page, size));
    }

    @Operation(summary = "전문가의 알림을 '읽음' 상태로 만듭니다.")
    @PatchMapping
    public ApiResponseDto<Long> updateExpertNotification(@AuthExpert Expert expert,
                                                   @RequestParam Long sourceId) {

        return ApiResponseDto.onSuccess(expertNotificationCommandService
                .readNotification(expert.getId(), sourceId));
    }
}
