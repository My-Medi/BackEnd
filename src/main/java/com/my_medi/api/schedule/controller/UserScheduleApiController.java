package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "사용자 스케줄 API")
@RestController
@RequestMapping("/api/v1/users/schedules")
@RequiredArgsConstructor
public class UserScheduleApiController {

    private final ScheduleQueryService scheduleQueryService;


    @Operation(summary = "사용자가 본인 스케줄을 조회합니다.")
    @GetMapping
    public ApiResponseDto<ScheduleResponseDto.ScheduleListDto> getAllSchedule(@RequestParam Long userId) {
        List<Schedule> userSchedules = scheduleQueryService.getUserSchedules(userId);
        return ApiResponseDto.onSuccess(ScheduleMapper.toScheduleListDto(userSchedules));
    }
}
