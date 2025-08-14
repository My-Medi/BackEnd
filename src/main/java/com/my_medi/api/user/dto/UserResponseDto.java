package com.my_medi.api.user.dto;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDto {

    @Data
    @Builder
    public static class UserInfoDto{
        private Long userId;
        private String name;
        private String nickname;
        private String birthDate;
        private Gender gender;
        private String email;
        private String phoneNumber;
        private String profileImgUrl;
        private Float height;
        private Float weight;
    }

    @Data
    @Builder
    public static class UserProfileTopDto{
        private String name;
        private String nickname;
        private String profileImgUrl;
        private int age;
        private Float height;
        private Float weight;
        private long reportCount; // 건강검진 횟수
    }

    @Data
    @Builder
    public static class RequestingUserInfoDto{
        private Long userId;
        private LocalDate accountRegisterDate; //회원가입 날짜
        private String nickname; // 닉네임
        private int age; //나이
        private Gender gender; //성별
        private Float height; //키
        private Float weight; //몸무게
        private String goal;
        private LocalDate reportRegisterDate; // 국가건강검진일 - 리포트
        private String requestNote; //요청사항 - proposal
        private List<String> healthInterests; // 건강 관심 분야 - proposal
        private List<String> abnormalCheckItems; // 건강검진 이상 수치 - proposal
    }
}
