package com.my_medi.api.consultation.dto;

import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.member.entity.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExpertConsultationDto {

    @Data
    @Builder
    public static class ExpertConsultationSummaryDto {
        private Long consultationId;
        private Long userId;
        private String comment;
        private String nickname;
        private String age;
        private Gender gender;
        private Float weight;
        private Float height;
    }

    @Data
    @Builder
    public static class ExpertConsultationPageDto<T> {
        private List<T> content;
        private String name;
        private String nickname;
        private int totalPages;
    }

    @Data
    @Builder
    public static class ExpertConsultationAcceptedDto {
        private Long consultationId;
        private Long userId;
        private HealthStatus totalHealthStatus;
        private String nickname;
        private Gender gender;
        private Float weight;
        private Float height;
        private String age;
        private String profileImage;

        private LocalDate recentCheckupDate;
        private List<String> interestAreas;

    }
}
