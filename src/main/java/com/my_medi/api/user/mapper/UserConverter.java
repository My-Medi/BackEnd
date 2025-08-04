package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.domain.user.entity.User;

public class UserConverter {

    public static UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                //TODO 채워넣기
                .id(user.getId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .profileImgUrl(user.getProfileImgUrl())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }


    public static UserResponseDto.UserProfileTopDto toUserProfileTopDto(User user){
        return UserResponseDto.UserProfileTopDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .ageUser(BirthDateUtil.getAge(user.getBirthDate()))
                .height(user.getHeight())
                .weight(user.getWeight())
                //TODO: 건강검진횟수(리포트 개수)
                //TODO: 건강 상태 : 리포트 결과 데이터(entity 추가)
                .build();
    }
}
