package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleSummaryDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "전문가 스케줄 API")
@RestController
@RequestMapping("/api/v1/experts/schedules")
@RequiredArgsConstructor
public class ExpertScheduleApiController {

    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    @Operation(summary = "전문가가 매칭된 유저에게 스케줄을 넣어줍니다")
    @PostMapping("/users/{userId}")
    public ApiResponseDto<Long> addScheduleToUser(
            @RequestParam Long expertId,
            @PathVariable Long userId,
            @RequestBody RegisterScheduleDto registerScheduleDto) {
        return ApiResponseDto.onSuccess(scheduleCommandService
                .registerScheduleToUser(expertId, userId, registerScheduleDto));
    }

//    @Operation(summary = "본인 스케줄을 모두 확인합니다")
//    @GetMapping
//    public ApiResponseDto<ScheduleResponseDto.ScheduleListDto> getAllSchedule(@RequestParam Long expertId) {
//        List<Schedule> expertSchedules = scheduleQueryService.getExpertSchedules(expertId);
//        return ApiResponseDto.onSuccess(ScheduleMapper.toScheduleListDto(expertSchedules));
//    }

}
