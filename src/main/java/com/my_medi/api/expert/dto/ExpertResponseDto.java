package com.my_medi.api.expert.dto;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.license.dto.LicenseResponseDto;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ExpertResponseDto{

    @Data
    @Builder
    public static class ExpertProfileDto{
        // Member 공통 필드
        private String name;
        private LocalDate birthDate;
        private Gender gender;
        private String nickname;
        private String phoneNumber;
        private String profileImgUrl;
        private Role role;

        // Expert 전용 필드
        private Specialty specialty;
        private String organizationName;
        private String licenseFileUrl;
        private String introduction;

        // 커리어 리스트
        private List<CareerResponseDto> careers;

        // 자격증 리스트
        private List<LicenseResponseDto> licenses;


    }
}