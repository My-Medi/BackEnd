package com.my_medi.api.consultation.mapper;


import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;

public class ExpertConsultationConverter {

    public static ExpertConsultationDto toExpertConsultationDto(ConsultationRequest request) {
        return ExpertConsultationDto.builder()
                .id(request.getId())
                .comment(request.getComment())
                .build();
    }
}
