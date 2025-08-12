package com.my_medi.api.expert.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileDto {
    // Member 공통 필드
    private String name;
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String profileImgUrl;
    private String loginId;
    private String password;
}

