package com.my_medi.domain.expert.dto;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import com.my_medi.domain.member.entity.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateResumeDto {
    // 이력서 수정 분리
    private Specialty specialty;
    private String organizationName;
    private String introduction;
    private String introSentences;

    private List<CareerRequestDto> careers;
    private List<LicenseRequestDto> licenses;
    private List<LicenseImageRequestDto> licenseImages;
}

