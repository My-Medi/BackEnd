package com.my_medi.api.consultation.dto;

import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserConsultationDto {
    private Long id;
    private String comment;
    private RequestStatus status;
}
