package com.my_medi.api.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {

    @Data
    @Builder
    public static class ScheduleSummaryDto{
        //TODO 기획보면서 필요한 데이터 채워넣기
//        private Long id;
//        private String title;
//        private String description;

    }

    @Data
    @Builder
    public static class ScheduleListDto{
        private List<ScheduleSummaryDto> scheduleSummaryDto;
    }
}
