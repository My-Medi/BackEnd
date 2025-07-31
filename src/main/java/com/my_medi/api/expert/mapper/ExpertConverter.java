package com.my_medi.api.expert.mapper;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.domain.expert.entity.Expert;

public class ExpertConverter {

    public static ExpertResponseDto.ExpertProfileDto toExpertProfileDto(Expert expert) {
        return ExpertResponseDto.ExpertProfileDto.builder()
                .name(expert.getName())
                .birthDate(expert.getBirthDate())
                .gender(expert.getGender())
                .nickname(expert.getNickname())
                .phoneNumber(expert.getPhoneNumber())
                .profileImgUrl(expert.getProfileImgUrl())
                .role(expert.getRole())
                .specialty(expert.getSpecialty())
                .organizationName(expert.getOrganizationName())
                .licenseFileUrl(expert.getLicenseFileUrl())
                .introduction(expert.getIntroduction())
                // career 추가
                .careers(
                        expert.getCareers().stream()
                                .map(c -> CareerRequestDto.builder()
                                        .companyName(c.getCompanyName())
                                        .jobTitle(c.getJobTitle())
                                        .startDate(c.getStartDate())
                                        .endDate(c.getEndDate())
                                        .build())
                                .toList()
                )

                .build();
    }

}
