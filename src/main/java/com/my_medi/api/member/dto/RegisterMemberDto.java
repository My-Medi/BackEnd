package com.my_medi.api.member.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegisterMemberDto {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private String loginId;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImgUrl;
}
