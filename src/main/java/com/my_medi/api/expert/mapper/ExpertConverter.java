package com.my_medi.api.expert.mapper;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.license.dto.LicenseResponseDto;
import com.my_medi.api.licenseImage.dto.LicenseImageResponseDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileListDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertSummaryProfileDto;
import com.my_medi.domain.expert.entity.Expert;
import org.springframework.data.domain.Page;

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
                .introduction(expert.getIntroduction())
                .introSentence(expert.getIntroSentence())
                // career 추가
                .careers(
                        expert.getCareers().stream()
                                .map(c -> CareerResponseDto.builder()
                                        .careerid(c.getId())
                                        .companyName(c.getCompanyName())
                                        .jobTitle(c.getJobTitle())
                                        .startDate(c.getStartDate())
                                        .endDate(c.getEndDate())
                                        .build())
                                .toList()
                )
                // licenseImages 추가
                .licenseImages(
                        expert.getLicenseImages().stream()
                                .map(img -> LicenseImageResponseDto.builder()
                                        .licenseImageId(img.getId())
                                        .imageUrl(img.getImageUrl())
                                        .imageTitle(img.getImageTitle())
                                        .build()
                                )
                                .toList()
                )

                // license 추가
                .licenses(
                        expert.getLicenses().stream()
                                .map(l -> LicenseResponseDto.builder()
                                        .id(l.getId())
                                        .licenseName(l.getLicenseName())
                                        .licenseDate(l.getLicenseDate())
                                        .licenseDescription(l.getLicenseDescription())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }

    public static ExpertResponseDto.ExpertProfileTopDto toExpertProfileTopDto(Expert expert) {
        return ExpertResponseDto.ExpertProfileTopDto.builder()
                .nickname(expert.getNickname())
                .name(expert.getName())
                .age(BirthDateUtil.getAge(expert.getBirthDate())) // 나이
                .specialty(expert.getSpecialty())
                .build();
    }

    public static ExpertResponseDto.ExpertDetailForUserDto toExpertDetailForUserDto(Expert expert) {
        return ExpertResponseDto.ExpertDetailForUserDto.builder()
                .nickname(expert.getNickname())
                .name(expert.getName())
                .profileImgUrl(expert.getProfileImgUrl())
                .introduction(expert.getIntroduction())
                .organizationName(expert.getOrganizationName())
                .specialty(expert.getSpecialty())

                // TODO career response 변경(date-> 개월수 계산 적용)
                .careers(
                        expert.getCareers().stream()
                                .map(r -> CareerResponseDto.builder()
                                        .careerid(r.getId())
                                        .companyName(r.getCompanyName())
                                        .jobTitle(r.getJobTitle())
                                        .startDate(r.getStartDate())
                                        .endDate(r.getEndDate())
                                        .build())
                                .toList()
                )
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
