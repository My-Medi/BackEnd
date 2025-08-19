package com.my_medi.api.expertNotification.controller;

import com.my_medi.api.common.service.SseService;
import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.ExpertNotificationSimplePageResponse;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.expertNotification.mapper.ExpertNotificationConverter;
import com.my_medi.api.expertNotification.service.ExpertNotificationUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[전문가 페이지]전문가 알림 API")
@RestController
@RequestMapping("/api/v1/experts/notifications")
@RequiredArgsConstructor
public class ExpertNotificationApiController {
    private final ExpertNotificationUseCase expertNotificationUseCase;
    private final ExpertNotificationCommandService expertNotificationCommandService;
    private final SseService sseService;

    @Operation(summary = "전문가의 알림을 pagination 으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<ExpertNotificationSimplePageResponse> getExpertNotificationByPage(
            @AuthExpert Expert expert,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {

        //TODO service method명 직관적으로 변경
        Page<ExpertNotification> expertNotificationPage = expertNotificationUseCase
                .getPrioritizedNotificationDtoPageByExpertId(expert.getId(), currentPage, pageSize);

        return ApiResponseDto.onSuccess(
                ExpertNotificationConverter.toExpertNotificationPageDto(expertNotificationPage)
        );
    }

    @Operation(summary = "전문가의 알림을 '읽음' 상태로 만듭니다.")
    @PatchMapping("/{notificationId}")
    public ApiResponseDto<Long> updateExpertNotification(@PathVariable Long notificationId) {

        return ApiResponseDto.onSuccess(expertNotificationCommandService
                .readExpertNotification(notificationId));
    }

    @Operation(summary = "전문가의 알림을 삭제합니다.")
    @DeleteMapping
    public ApiResponseDto<Void> deleteExpertNotifications(@RequestParam List<Long> notificationId) {
        expertNotificationCommandService.removeNotifications(notificationId);

        return ApiResponseDto.onSuccess(null);
    }

    @Operation(summary = "사용자 알림 실시간 조회를 위해 sse 연결을 합니다.")
    @GetMapping("/stream")
    public ApiResponseDto<String> linkUserAtSse(@AuthUser User user) {
        sseService.connectUser(user.getId());
        return ApiResponseDto.onSuccess(HttpStatus.OK.toString());
    }
}
