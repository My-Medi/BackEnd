package com.my_medi.api.schedule.mapper;

import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleListDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleSummaryDto;
import com.my_medi.domain.schedule.entity.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleMapper {

    public static ScheduleSummaryDto toScheduleSummaryDto(Schedule schedule) {
        return ScheduleSummaryDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }

    public static ScheduleListDto toScheduleListDto(List<Schedule> schedules) {
        List<ScheduleSummaryDto> summaryList = schedules.stream()
                .map(ScheduleMapper::toScheduleSummaryDto)
                .collect(Collectors.toList());

        return ScheduleListDto.builder()
                .scheduleSummaryDto(summaryList)
                .build();
    }
}

