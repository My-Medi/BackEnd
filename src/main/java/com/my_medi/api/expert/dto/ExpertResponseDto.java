package com.my_medi.api.expert.dto;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.license.dto.LicenseResponseDto;
import com.my_medi.api.licenseImage.dto.LicenseImageResponseDto;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ExpertResponseDto{

    @Data
    @Builder
    public static class ExpertProfileDto{
        private Long expertId;
        // Member 공통 필드
        private String name;
        private String email;
        private String birthDate;
        private Gender gender;
        private String nickname;
        private String phoneNumber;
        private String profileImgUrl;
        private Role role;

        // Expert 전용 필드
        private Specialty specialty;
        private String organizationName;
        private String introduction;
        private String introSentence;

    }

    @Data
    @Builder
    public static class ExpertProfileTopDto{
        private Long expertId;
        private String nickname; //닉네임
        private String name; // 이름
        private int age; // expert의 나이
        private Specialty specialty;// 전문 분야

    }

    @Data
    @Builder
    public static class ExpertDetailForUserDto{
        private Long expertId;
        private String nickname; //닉네임
        private String name; // 이름
        private String profileImgUrl; // 프로필 이미지
        private String introduction; // 전문가 소개
        private String organizationName; // 소속 회사
        private Specialty specialty; // 분야
        private List<CareerResponseDto> careers; // 경력사항

    }

    @Data
    @Builder
    public static class ExpertSummaryProfileDto{
        private Long expertId;
        private Specialty specialty;
        private String name;
        private String nickname;
        private String profileImgUrl;

        private String introduction;
        private String organizationName;
        @Builder.Default
        private List<CareerResponseDto> careerResponseDtoList = new ArrayList<>();
    }

    @Data
    @Builder
    public static class ExpertProfileListDto{
        @Builder.Default
        private List<ExpertSummaryProfileDto> expertSummaryProfileDtoList = new ArrayList<>();
        private final int page;
        private final int totalPages;
    }
    //TODO : GET/RESUME api dto 따로 만들 예정

    /*        // 커리어 리스트
        private List<CareerResponseDto> careers;

        // 자격증 증명사진 리스트
        private List<LicenseImageResponseDto> licenseImages;

        // 자격증 리스트
        private List<LicenseResponseDto> licenses;*/
}