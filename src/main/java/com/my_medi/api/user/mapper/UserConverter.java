package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;

public class UserConverter {

    public static UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                //TODO 채워넣기
                .userid(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .profileImgUrl(user.getProfileImgUrl())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }


    public static UserResponseDto.UserProfileTopDto toUserProfileTopDto(User user, long reportCount){
        return UserResponseDto.UserProfileTopDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .age(BirthDateUtil.getAge(user.getBirthDate()))
                .height(user.getHeight())
                .weight(user.getWeight())
                .reportCount(reportCount)
                .build();
    }
}
