package com.my_medi.api.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {

    @Data
    @Builder
    public static class ScheduleSummaryDto{
        private Long id;
        private String title;
        private LocalDate date;

    }

    @Data
    @Builder
    public static class ScheduleDetailDto{
        private Long id;
        private String title;
        private String memo;
        private String location;
        private LocalDate date;
        private int hour;
        private int minute;
        private boolean isAm;

    }

    @Data
    @Builder
    public static class ScheduleListDto{
        private List<ScheduleSummaryDto> scheduleSummaryDto;
    }

    @Data
    @Builder
    public static class ScheduleDetailListDto{
        private List<ScheduleDetailDto> scheduleDetailDto;
    }
}
