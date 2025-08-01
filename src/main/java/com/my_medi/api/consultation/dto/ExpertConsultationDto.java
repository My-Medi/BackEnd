package com.my_medi.api.consultation.dto;

import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.member.entity.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ExpertConsultationDto {

    @Data
    @Builder
    public static class ExpertConsultationSummaryDto {
        private Long id;
        private String comment;
        private RequestStatus status;
        private String nickname;
        private Gender gender;
        private Float weight;
        private Float height;
    }

    @Data
    @Builder
    public static class ExpertConsultationPageDto {
        private List<ExpertConsultationSummaryDto> content;
        private int totalPages;
    }
}
