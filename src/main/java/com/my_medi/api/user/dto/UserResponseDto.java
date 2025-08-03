package com.my_medi.api.user.dto;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

public class UserResponseDto {

    @Data
    @Builder
    public static class UserProfileDto{
        //TODO 채워넣기 유저랑 전문가 계정 등록하는 api 생성 jwt security 설정
        private Long id;
        private String name;
        private String birthDate;
        private Gender gender;
        private String email;
        private String phoneNumber;
        private String profileImgUrl;
        private String username;
        private Role role;
    }
}
