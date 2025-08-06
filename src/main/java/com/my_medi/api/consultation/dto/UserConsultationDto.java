package com.my_medi.api.consultation.dto;

import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.expert.entity.Specialty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class UserConsultationDto {

    @Data
    @Builder
    public static class UserConsultationStatusDto {
        private Long consultationId;
        private Long expertId;
        private RequestStatus status;
        private Specialty specialty;
        private String name;
        private String nickname;
        private String profileImgUrl;
        private String phoneNumber;
        private String introSentence;
        private LocalDate requestDate;
    }

    @Data
    @Builder
    public static class ExpertRequestedDto {
        private Long expertId;
        private String name;
        private String nickname;
        private String phoneNumber;
        private String introSentence;
        private String profileImageUrl;
        private LocalDate requestedAt;
        private String introduction;
        private String organization;
        private Specialty specialty;
        private List<String> career;
    }
}
