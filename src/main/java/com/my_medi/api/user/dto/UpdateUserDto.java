package com.my_medi.api.user.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.Getter;

@Getter
public class UpdateUserDto {
    private String name;
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String profileImgUrl;
    private Float height;
    private Float weight;
    private String loginId;
    private String password;
}
