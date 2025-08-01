package com.my_medi.api.expert.mapper;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileListDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertSummaryProfileDto;
import com.my_medi.domain.expert.entity.Expert;
import org.springframework.data.domain.Page;

public class ExpertConverter {

    public static ExpertProfileDto toExpertProfileDto(Expert expert) {
        return ExpertProfileDto.builder()
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

    public static ExpertSummaryProfileDto toExpertSummaryProfileDto(Expert expert) {
        return ExpertSummaryProfileDto.builder()
                .expertId(expert.getId())
                .introduction(expert.getIntroduction())
                .specialty(expert.getSpecialty())
                .organizationName(expert.getOrganizationName())
                .name(expert.getName())
                .nickname(expert.getNickname())
                .build();
    }

    public static ExpertProfileListDto toExpertProfileListDto(Page<Expert> experts) {
        return ExpertProfileListDto.builder()
                .expertSummaryProfileDtoList(
                        experts.getContent().stream()
                                .map(ExpertConverter::toExpertSummaryProfileDto)
                                .toList()
                )
                .totalPages(experts.getTotalPages())
                .page(experts.getNumber())
                .build();
    }

}
