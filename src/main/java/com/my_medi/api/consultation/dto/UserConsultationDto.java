package com.my_medi.api.consultation.dto;

import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.expert.entity.Specialty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserConsultationDto {
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
