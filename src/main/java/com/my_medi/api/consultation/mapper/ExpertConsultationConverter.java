package com.my_medi.api.consultation.mapper;

import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.user.entity.User;

public class ExpertConsultationConverter {

    public static ExpertConsultationDto.ExpertConsultationSummaryDto toExpertConsultationDto(ConsultationRequest request) {

        User user = request.getUser();

        return ExpertConsultationDto.ExpertConsultationSummaryDto.builder()
                .id(request.getId())
                .comment(request.getComment())
                .status(request.getRequestStatus())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }
}
