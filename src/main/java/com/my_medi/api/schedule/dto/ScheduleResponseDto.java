package com.my_medi.api.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {

    @Data
    @Builder
    public static class ScheduleSummaryDto{
        private Long id;
        private String title;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

    }

    @Data
    @Builder
    public static class ScheduleListDto{
        private List<ScheduleSummaryDto> scheduleSummaryDto;
    }
}
