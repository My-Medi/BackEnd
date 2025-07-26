package com.my_medi.api.ExpertNotification.controller;

import com.my_medi.api.ExpertNotification.dto.ExpertNotificationResponseDto.*;
import com.my_medi.api.ExpertNotification.mapper.ExpertNotificationConverter;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import com.my_medi.domain.expertNotification.service.ExpertNotificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[전문가 페이지]전문가 알림 API")
@RestController
@RequestMapping("/api/v1/experts/notifications")
@RequiredArgsConstructor
public class ExpertNotificationApiController {
    private final ExpertNotificationQueryService expertNotificationQueryService;

    @Operation(summary = "전문가의 알림을 조회합니다.")
    @GetMapping
    public ApiResponseDto<List<ExpertNotificationDto>> getExpertNotification(@AuthExpert Expert expert) {
        List<ExpertNotification> notificationList = expertNotificationQueryService
                .getNotificationByExpertId(expert.getId());

        return ApiResponseDto.onSuccess(ExpertNotificationConverter.toExpertNotificationListDto(notificationList));
    }
}
