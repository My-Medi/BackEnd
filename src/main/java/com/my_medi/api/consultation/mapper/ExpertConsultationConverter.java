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
                .nickname(user.getNickname())
                .gender(user.getGender())
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
        //TODO: 나이 추가
    }

    public static ExpertConsultationDto.ExpertConsultationAcceptedDto toAcceptedConsultationDto(ConsultationRequest request) {
        User user = request.getUser();

        return ExpertConsultationDto.ExpertConsultationAcceptedDto.builder()
                .id(request.getId())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .profileImage(user.getProfileImgUrl())
                //TODO: 나이, 최근 건강검진일, 건강관심분야 추가
                .build();
    }

}
