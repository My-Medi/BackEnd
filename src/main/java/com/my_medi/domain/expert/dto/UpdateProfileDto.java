package com.my_medi.domain.expert.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileDto {

    // Member 공통 필드
    private String name;
    private String birthDate;
    private Gender gender;
    private String nickname;
    private String phoneNumber;
    private String profileImgUrl;

    // Expert 전용 필드 사라짐. 회원정보수정, 이력서 수정 분리
    //private Specialty specialty;
    //private String organizationName;
    //private String introduction;
}

