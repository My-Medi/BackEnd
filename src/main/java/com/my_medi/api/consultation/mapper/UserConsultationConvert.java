package com.my_medi.api.consultation.mapper;

import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.expert.entity.Expert;
import static com.my_medi.common.util.PeriodUtil.calculateMonthsBetween;



public class UserConsultationConvert {

    public static UserConsultationDto.UserConsultationStatusDto toDto(ConsultationRequest request) {
        Expert expert = request.getExpert();
        return UserConsultationDto.UserConsultationStatusDto.builder()
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

    public static UserConsultationDto.ExpertRequestedDto toDetailDto(ConsultationRequest request) {
        Expert expert = request.getExpert();

        return UserConsultationDto.ExpertRequestedDto.builder()
                .expertId(expert.getId())
                .name(expert.getName())
                .nickname(expert.getNickname())
                .phoneNumber(expert.getPhoneNumber())
                .introSentence(expert.getIntroSentence())
                .profileImageUrl(expert.getProfileImgUrl())
                .matchedAt(request.getCreatedDate().toLocalDate())
                .introduction(expert.getIntroduction())
                .organization(expert.getOrganizationName())
                .specialty(expert.getSpecialty())
                .career(
                        expert.getCareers().stream()
                                .map(career -> String.format(
                                        "%s %d개월",
                                        career.getJobTitle(),
                                        calculateMonthsBetween(career.getStartDate(), career.getEndDate())
                                ))
                                .toList()
                )
                .build();
    }
}
