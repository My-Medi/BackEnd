package com.my_medi.api.consultation.mapper;

import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.FormUtil;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.user.entity.User;

public class ExpertConsultationConverter {

    public static ExpertConsultationDto.ExpertConsultationSummaryDto toExpertConsultationDto(ConsultationRequest request) {

        User user = request.getUser();

        return ExpertConsultationDto.ExpertConsultationSummaryDto.builder()
                .consultationId(request.getId())
                .userId(user.getId())
                .comment(request.getComment())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .weight(user.getWeight())
                .height(user.getHeight())
                .age(FormUtil.formatAge(user.getBirthDate()))
                .build();
    }

    public static ExpertConsultationDto.ExpertConsultationAcceptedDto toAcceptedConsultationDto(ConsultationRequest request) {
        User user = request.getUser();

        return ExpertConsultationDto.ExpertConsultationAcceptedDto.builder()
                .consultationId(request.getId())
                .userId(user.getId())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .profileImage(user.getProfileImgUrl())
                .age(FormUtil.formatAge(user.getBirthDate()))
                //TODO: 최근 건강검진일, 건강관심분야 추가 <- 작업 추가해주세요
                .build();
    }

}
