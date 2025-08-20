package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "[사용자 페이지] 스케줄 API")
@RestController
@RequestMapping("/api/v1/users/schedules")
@RequiredArgsConstructor
public class UserScheduleApiController {

    private final ScheduleQueryService scheduleQueryService;

    @Operation(summary = "사용자가 본인 스케줄을 월 단위로 조회합니다.")
    @GetMapping
    public ApiResponseDto<ScheduleResponseDto.ScheduleListDto> getMonthlySchedule(
            @AuthUser User user,
            @RequestParam int year,
            @RequestParam int month) {

        List<Schedule> userSchedules = scheduleQueryService.getUserSchedulesByMonth(user.getId(), year, month);
        return ApiResponseDto.onSuccess(ScheduleMapper.toScheduleListDto(userSchedules));
    }

    @Operation(summary = "특정 날짜의 사용자의 일정 목록을 조회합니다")
    @GetMapping("/date")
    public ApiResponseDto<List<ScheduleResponseDto.ScheduleDetailDto>> getSchedulesByDate(
            @AuthUser User user,
            @Parameter(description = "조회할 날짜 (형식: YYYY-MM-DD)", schema = @Schema(defaultValue = "YYYY-MM-DD"))

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<Schedule> schedules = scheduleQueryService.getSchedulesByUserAndDate(user.getId(), date);

        List<ScheduleResponseDto.ScheduleDetailDto> dtoList = schedules.stream()
                .map(ScheduleMapper::toScheduleDetailDto)
                .collect(Collectors.toList());

        return ApiResponseDto.onSuccess(dtoList);
    }

}
