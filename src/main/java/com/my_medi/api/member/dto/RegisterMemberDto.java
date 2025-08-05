package com.my_medi.api.member.dto;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegisterMemberDto {
    private String name;
    private String birthDate;
    private Gender gender;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String profileImgUrl;
    private String loginId;
    private String password;


}
