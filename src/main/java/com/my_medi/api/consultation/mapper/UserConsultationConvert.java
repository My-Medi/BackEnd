package com.my_medi.api.consultation.mapper;

import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;

public class UserConsultationConvert {
    public static UserConsultationDto toDto(ConsultationRequest request) {
        return UserConsultationDto.builder()
                .id(request.getId())
                .comment(request.getComment())
                .status(request.getRequestStatus())
                .build();
    }
}