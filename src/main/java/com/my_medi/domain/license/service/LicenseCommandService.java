package com.my_medi.domain.license.service;

import com.my_medi.api.license.dto.LicenseRequestDto;

public interface LicenseCommandService {
    Long registerLicense(Long expertId, LicenseRequestDto dto); // 자격증 등록
    void updateLicense(Long licenseId, LicenseRequestDto dto); // 자격증 수정
    void deleteLicense(Long licenseId); // 자격증 삭제
}
