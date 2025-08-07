package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.api.schedule.service.ScheduleUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "[전문가 페이지] 스케줄 API")
@RestController
@RequestMapping("/api/v1/experts/schedules")
@RequiredArgsConstructor
public class ExpertScheduleApiController {

    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleUseCase scheduleUseCase;
    private final ScheduleQueryService scheduleQueryService;

    @Operation(summary = "전문가가 매칭된 유저에게 스케줄을 등록합니다.")
    @PostMapping("/users/{userId}")
    public ApiResponseDto<Long> addScheduleToUser(
            @AuthExpert Expert expert,
            @PathVariable Long userId,
            @RequestBody RegisterScheduleDto registerScheduleDto) {

        return ApiResponseDto.onSuccess(scheduleUseCase
                .registScheduleAndSendNotificationToUser(expert, userId, registerScheduleDto));
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

    @Operation(summary = "전문가의 가장 임박한 1개의 스케줄을 조회합니다.")
    @GetMapping("/upcoming")
    public ApiResponseDto<List<ScheduleResponseDto.ScheduleDetailDto>> getUpcomingSchedules(
            @AuthExpert Expert expert) {

        List<Schedule> upcomingSchedules = scheduleQueryService.getUpcomingSchedulesForExpert(expert.getId());

        List<ScheduleResponseDto.ScheduleDetailDto> dtoList =
                upcomingSchedules.stream()
                        .map(ScheduleMapper::toScheduleDetailDto)
                        .toList();

        return ApiResponseDto.onSuccess(dtoList);
    }

    @Operation(summary = "특정 날짜의 전문가 일정 목록을 조회합니다")
    @GetMapping("/date")
    public ApiResponseDto<List<ScheduleResponseDto.ScheduleDetailDto>> getSchedulesByDate(
            @AuthExpert Expert expert,
            @Parameter(description = "조회할 날짜 (형식: YYYY-MM-DD)", schema = @Schema(defaultValue = "YYYY-MM-DD"))

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<Schedule> schedules = scheduleQueryService.getSchedulesByExpertAndDate(expert.getId(), date);

        List<ScheduleResponseDto.ScheduleDetailDto> dtoList = schedules.stream()
                .map(ScheduleMapper::toScheduleDetailDto)
                .collect(Collectors.toList());

        return ApiResponseDto.onSuccess(dtoList);
    }

}
