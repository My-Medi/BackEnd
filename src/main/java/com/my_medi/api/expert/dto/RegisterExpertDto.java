package com.my_medi.api.expert.dto;
import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.expert.entity.Specialty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterExpertDto {

    // 회원 공통 정보
    @Valid
    private RegisterMemberDto member;

    // Expert 정보
    private Specialty specialty;           // 전문 분야(enum)
    private String organizationName;       // 소속 회사/기관명
    private String introduction;           // 경력 소개글
    // 나를 소개하는 대표 문장 한줄
    private String introSentence;

    // 경력 리스트
    @Builder.Default
    private List<CareerRequestDto> careers = new ArrayList<>();

    // 자격증 이미지 리스트
    @Builder.Default
    private List<LicenseImageRequestDto> licenseImages = new ArrayList<>();

    // 자격증 리스트
    @Builder.Default
    private List<LicenseRequestDto> licenses = new ArrayList<>();



}
