package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleSummaryDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[전문가 페이지] 스케줄 API")
@RestController
@RequestMapping("/api/v1/experts/schedules")
@RequiredArgsConstructor
public class ExpertScheduleApiController {

    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    @Operation(summary = "전문가가 매칭된 유저에게 스케줄을 등록합니다.")
    @PostMapping("/users/{userId}")
    public ApiResponseDto<Long> addScheduleToUser(
            @AuthExpert Expert expert,
            @PathVariable Long userId,
            @RequestBody RegisterScheduleDto registerScheduleDto) {
        return ApiResponseDto.onSuccess(scheduleCommandService
                .registerScheduleToUser(expert, userId, registerScheduleDto));
    }

    @Operation(summary = "전문가가 본인 스케줄을 월 단위로 조회합니다.")
    @GetMapping
    public ApiResponseDto<ScheduleResponseDto.ScheduleListDto> getMonthlySchedule(
            @AuthExpert Expert expert,
            @RequestParam int year,
            @RequestParam int month) {

        List<Schedule> expertSchedules = scheduleQueryService.getExpertSchedulesByMonth(expert.getId(), year, month);
        return ApiResponseDto.onSuccess(ScheduleMapper.toScheduleListDto(expertSchedules));
    }

    @Operation(summary = "전문가가 스케줄을 삭제합니다.")
    @DeleteMapping("/{scheduleId}")
    public ApiResponseDto<Long> removeSchedule(
            @AuthExpert Expert expert,
            @PathVariable Long scheduleId) {
        scheduleCommandService.removeSchedule(expert.getId(), scheduleId);
        return ApiResponseDto.onSuccess(scheduleId);
    }

    @Operation(summary = "전문가의 가장 임박한 3개의 스케줄을 조회합니다.")
    @GetMapping("/upcoming")
    public ApiResponseDto<List<ScheduleResponseDto.ScheduleSummaryDto>> getUpcomingSchedules(
            @AuthExpert Expert expert) {

        List<Schedule> upcomingSchedules = scheduleQueryService.getUpcomingSchedulesForExpert(expert.getId());

        List<ScheduleResponseDto.ScheduleSummaryDto> dtoList =
                upcomingSchedules.stream()
                        .map(ScheduleMapper::toScheduleSummaryDto)
                        .toList();

        return ApiResponseDto.onSuccess(dtoList);
    }

}
