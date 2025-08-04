package com.my_medi.api.expert.dto;

import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.license.dto.LicenseResponseDto;
import com.my_medi.api.licenseImage.dto.LicenseImageResponseDto;
import com.my_medi.domain.expert.entity.Specialty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ExpertResumeDto {
    // Expert 전용 필드
    private Specialty specialty;
    private String organizationName;
    private String licenseFileUrl;
    private String introduction;

    @Builder.Default
    private List<CareerResponseDto> careers = new ArrayList<>();

    // 자격증 증명사진 리스트
    @Builder.Default
    private List<LicenseImageResponseDto> licenseImages = new ArrayList<>();

    // 자격증 리스트
    @Builder.Default
    private List<LicenseResponseDto> licenses = new ArrayList<>();

}
