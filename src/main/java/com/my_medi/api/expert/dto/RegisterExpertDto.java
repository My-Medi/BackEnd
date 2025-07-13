package com.my_medi.api.expert.dto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.expert.entity.Specialty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RegisterExpertDto {

    // 회원 공통 정보
    private RegisterMemberDto member;

    // Expert 정보
    private Specialty specialty;           // 전문 분야(enum)
    private String organizationName;       // 소속 회사/기관명
    private String licenseFileUrl;         // 자격증 파일 링크(URL)
    private String introduction;           // 경력 소개글


}
