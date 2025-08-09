package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;

public class UserConverter {

    public static UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .userid(user.getId())
                .name(user.getName()) //성명
                .nickname(user.getNickname()) //닉네임
                .birthDate(user.getBirthDate()) //생년월일
                .gender(user.getGender()) //성별
                .email(user.getEmail()) //이메일
                .phoneNumber(user.getPhoneNumber()) //전화번호
                .profileImgUrl(user.getProfileImgUrl()) //프로필이미지
                .height(user.getHeight()) // 키
                .weight(user.getWeight()) // 몸무게
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
