package com.my_medi.api.consultation.mapper;

import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.expert.entity.Expert;


public class UserConsultationConvert {
    public static UserConsultationDto toDto(ConsultationRequest request) {
        Expert expert = request.getExpert();
        return UserConsultationDto.builder()
                .consultationId(request.getId())
                .expertId(expert.getId())
                .status(request.getRequestStatus())
                .specialty(expert.getSpecialty())
                .name(expert.getName())
                .nickname(expert.getNickname())
                .profileImgUrl(expert.getProfileImgUrl())
                .phoneNumber(expert.getPhoneNumber())
                .introSentence(expert.getIntroSentence())
                .requestDate(request.getCreatedDate().toLocalDate())
                .build();
    }
}
