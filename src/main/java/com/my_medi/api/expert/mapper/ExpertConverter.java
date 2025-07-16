package com.my_medi.api.expert.mapper;

import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.entity.User;

import java.time.LocalDate;

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
                .build();
    }

}
