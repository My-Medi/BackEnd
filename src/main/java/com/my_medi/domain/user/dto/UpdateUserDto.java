package com.my_medi.domain.user.dto;

import com.my_medi.domain.member.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//TODO class 배치 위치 선정 이유 설명해주기
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
