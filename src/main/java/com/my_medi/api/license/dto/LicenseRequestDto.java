package com.my_medi.api.license.dto;

import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.license.entity.License;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class LicenseRequestDto { //사용자로부터 입력 받는 값 (등록/수정 시)
    private String licenseName;
    private LocalDate licenseDate;
    private String licenseDescription;

    public License toEntity(Expert expert) {
        return License.builder()
                .expert(expert)
                .licenseName(this.licenseName)
                .licenseDate(this.licenseDate)
                .licenseDescription(this.licenseDescription)
                .build();
    }
}
