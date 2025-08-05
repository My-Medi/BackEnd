package com.my_medi.api.license.dto;

import com.my_medi.api.licenseImage.dto.LicenseImageResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class LicenseResponseDto { //사용자에게 반환할 값 (상세 조회/목록 조회)
    private Long id;
    private String licenseName;
    private LocalDate licenseDate;
    private String licenseDescription;
}
