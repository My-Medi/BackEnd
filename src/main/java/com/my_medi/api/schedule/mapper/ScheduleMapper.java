package com.my_medi.api.schedule.mapper;

import com.my_medi.api.schedule.dto.ScheduleResponseDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleDetailDto;
import com.my_medi.api.schedule.dto.ScheduleResponseDto.ScheduleDetailListDto;
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
                .meetingDate(schedule.getMeetingDate())
                .build();
    }

    public static ScheduleDetailDto toScheduleDetailDto(Schedule schedule) {
        return ScheduleDetailDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                //TODO : title template으로 바꾸기 및 엔티티 내의 title 필드 제거 <- 작업하기
                .memo(schedule.getMemo())
                .location(schedule.getLocation())
                .meetingDate(schedule.getMeetingDate())
                .hour(schedule.getHour())
                .minute(schedule.getMinute())
                .isAm(schedule.isAm())
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

    public static ScheduleDetailListDto toScheduleDetailListDto(List<Schedule> schedules) {
        List<ScheduleDetailDto> detailList = schedules.stream()
                .map(ScheduleMapper::toScheduleDetailDto)
                .collect(Collectors.toList());

        return ScheduleDetailListDto.builder()
                .scheduleDetailDto(detailList)
                .build();
    }
}
