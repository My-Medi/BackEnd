package com.my_medi.api.member.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class RegisterMemberDto {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private UUID username;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImgUrl;
}
