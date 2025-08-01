package com.my_medi.api.license.dto;

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
    // licenseImage 정보 담을 DTO 리스트
    private List<LicenseImageResponseDto> images;

    @Data
    @Builder
    public static class LicenseImageResponseDto {
        private Long id;
        private String imageUrl;
        private String imageTitle;
    }
}
