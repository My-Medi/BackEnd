package com.my_medi.domain.expert.dto;

import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.entity.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateExpertDto {

    // Member 공통 필드
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private String phoneNumber;
    private String profileImgUrl;

    // Expert 전용 필드
    private Specialty specialty;
    private String organizationName;
    private String licenseFileUrl;
    private String introduction;
}

