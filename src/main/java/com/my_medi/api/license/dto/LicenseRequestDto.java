package com.my_medi.api.license.dto;

import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
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
}
