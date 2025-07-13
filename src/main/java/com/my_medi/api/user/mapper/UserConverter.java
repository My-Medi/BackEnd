package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
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
}
