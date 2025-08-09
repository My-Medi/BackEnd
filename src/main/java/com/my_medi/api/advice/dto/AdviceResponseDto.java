package com.my_medi.api.advice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class AdviceResponseDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdviceDto {
        private Long adviceId;
        private Long userId;
        private Long expertId;
        private String adviceComment;
        private LocalDateTime createdDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdviceSimplePageResponse {
        private List<AdviceDto> adviceList;
        private Integer totalPages;
        private Integer page;
    }
}
