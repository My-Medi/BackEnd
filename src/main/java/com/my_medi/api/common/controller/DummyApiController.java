package com.my_medi.api.common.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.expert.service.ExpertCommandService;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "더미 데이터 생성 API", description = "더미데이터 생성 API")
@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class DummyApiController {

    private final ExpertCommandService expertCommandService;
    private final ExpertNotificationCommandService expertNotificationCommandService;
    private final UserNotificationCommandService userNotificationCommandService;
    private final ScheduleCommandService scheduleCommandService;

    @Operation(summary = "전문가 더미 생성 API")
    @PostMapping("/experts")
    public ApiResponseDto<String> createExpertsDummy(@RequestParam int count) {
        expertCommandService.registerDummyExperts(count);
        return ApiResponseDto.onSuccess("success");
    }

    @Operation(summary = "알림 더미 생성 API", description = "send to user")
    @PostMapping("/notifications/users/{userId}")
    public ApiResponseDto<String> createNotificationToUserDummy(@PathVariable Long userId,
                                                                @RequestParam int count) {
        userNotificationCommandService.sendDummyNotificationToUser(userId, count);
        return ApiResponseDto.onSuccess("success");
    }

    @Operation(summary = "알림 더미 생성 API", description = "send to expert")
    @PostMapping("/notifications/experts/{expertId}")
    public ApiResponseDto<String> createNotificationToExpertDummy(@PathVariable Long expertId,
                                                                  @RequestParam int count) {
        expertNotificationCommandService.sendDummyNotificationToExpert(expertId, count);
        return ApiResponseDto.onSuccess("success");
    }

    @Operation(summary = "스케줄 더미 생성 API")
    @PostMapping("/schedules/experts/{expertId}/users/{userId}")
    public ApiResponseDto<String> createScheduleDummy(@PathVariable Long expertId,
                                                      @PathVariable Long userId,
                                                      @RequestParam int year,
                                                      @RequestParam int month,
                                                      @RequestParam int count) {
        scheduleCommandService.createScheduleDummy(expertId, userId, year, month, count);
        return ApiResponseDto.onSuccess("success");
    }
}
