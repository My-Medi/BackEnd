package com.my_medi.api.expert.mapper;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.license.dto.LicenseResponseDto;
import com.my_medi.api.licenseImage.dto.LicenseImageResponseDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileListDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertSummaryProfileDto;
import com.my_medi.common.util.FormUtil;
import com.my_medi.domain.expert.entity.Expert;
import org.springframework.data.domain.Page;

public class ExpertConverter {
    public static ExpertResponseDto.ExpertInfoDto toExpertInfoDto(Expert expert) {
        return ExpertResponseDto.ExpertInfoDto.builder()
                .expertId(expert.getId())
                .name(expert.getName()) // 성명
                .birthDate(expert.getBirthDate()) //생년월일
                .gender(expert.getGender()) // 성별
                .nickname(expert.getNickname()) //닉네임
                .email(expert.getEmail()) //이메일
                .phoneNumber(expert.getPhoneNumber()) //전화번호
                .profileImgUrl(expert.getProfileImgUrl()) //프로필이미지
                .role(expert.getRole())
                .specialty(expert.getSpecialty())
                .organizationName(expert.getOrganizationName())
                .introduction(expert.getIntroduction())
                .introSentence(expert.getIntroSentence())
                .build();
    }

    public static ExpertResponseDto.ExpertProfileTopDto toExpertProfileTopDto(Expert expert) {
        return ExpertResponseDto.ExpertProfileTopDto.builder()
                .expertId(expert.getId())
                .nickname(expert.getNickname())
                .name(expert.getName())
                .age(BirthDateUtil.getAge(expert.getBirthDate())) // 나이
                .specialty(expert.getSpecialty())
                .build();
    }

    public static ExpertResponseDto.ExpertDetailForUserDto toExpertDetailForUserDto(Expert expert) {
        return ExpertResponseDto.ExpertDetailForUserDto.builder()
                .expertId(expert.getId())
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
                                        .careerId(r.getId())
                                        .companyName(r.getCompanyName())
                                        .jobTitle(r.getJobTitle())
                                        .startDate(r.getStartDate())
                                        .endDate(r.getEndDate())
                                        .build())
                                .toList()
                )
                .build();
    }

    public static ExpertResponseDto.ExpertResumeDto toExpertResumeDto(Expert expert) {
        return ExpertResponseDto.ExpertResumeDto.builder()
                .specialty(expert.getSpecialty())
                .organizationName(expert.getOrganizationName())
                .introduction(expert.getIntroduction())
                .introSentences(expert.getIntroSentence())
                .careers(
                        expert.getCareers().stream()
                                .map(c -> CareerResponseDto.builder()
                                        .careerId(c.getId())
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


    public static ExpertSummaryProfileDto toExpertSummaryProfileDto (Expert expert){
        return ExpertSummaryProfileDto.builder()
                .expertId(expert.getId())
                .introduction(expert.getIntroduction())
                .introSentence(expert.getIntroSentence())
                .specialty(expert.getSpecialty())
                .profileImgUrl(expert.getProfileImgUrl())
                .organizationName(expert.getOrganizationName())
                .name(expert.getName())
                .nickname(expert.getNickname())
                .career(
                        expert.getCareers().stream()
                                .map(career -> FormUtil.formatCareerPeriod(
                                        career.getJobTitle(),
                                        career.getStartDate(),
                                        career.getEndDate()
                                ))
                                .toList()
                )
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


