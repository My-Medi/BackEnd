package com.my_medi.api.schedule.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.mapper.ScheduleMapper;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.service.ScheduleQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[사용자 페이지] 스케줄 API")
@RestController
@RequestMapping("/api/v1/users/schedules")
@RequiredArgsConstructor
public class UserScheduleApiController {

    private final ScheduleQueryService scheduleQueryService;


    //TODO 월 단위로 조회 가능하도록 수정
    @Operation(summary = "사용자가 본인 스케줄을 조회합니다.")
    @GetMapping
    public ApiResponseDto<ScheduleResponseDto.ScheduleListDto> getAllSchedule(@AuthUser User user) {
        List<Schedule> userSchedules = scheduleQueryService.getUserSchedules(user.getId());
        return ApiResponseDto.onSuccess(ScheduleMapper.toScheduleListDto(userSchedules));
    }

    //TODO 대표 스케줄 3개 조회(상세 내용)
}
