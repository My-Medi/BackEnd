package com.my_medi.domain.expert.dto;

import com.my_medi.domain.member.entity.Gender;
import lombok.*;

import java.time.LocalDate;

//TODO 해당 클래스 배치 위치 선정 이유 설명해주기
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


    // Expert 전용 필드 사라짐. 회원정보수정, 이력서 수정 분리
    //private Specialty specialty;
    //private String organizationName;
    //private String introduction;
}

