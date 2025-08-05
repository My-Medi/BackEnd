package com.my_medi.domain.license.service;

import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.license.mapper.LicenseConverter;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LicenseCommandServiceImpl implements LicenseCommandService {

    private final LicenseRepository licenseRepository;
    private final ExpertRepository expertRepository;
    private final LicenseConverter licenseConverter;

    @Override
    public Long registerLicense(Long expertId, LicenseRequestDto dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        License license = licenseConverter.toEntity(dto, expert);
        licenseRepository.save(license);
        return license.getId();
    }

    @Override
    public void updateLicense(Long licenseId, LicenseRequestDto dto) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND); // TODO : 추후 license 핸들러로 변경예정
        license.update(dto);
    }

    @Override
    public void deleteLicense(Long licenseId) {
        if (!licenseRepository.existsById(licenseId)) {
            throw ExpertHandler.NOT_FOUND; // TODO : 추후 license 핸들러로 변경예정
        }
        licenseRepository.deleteById(licenseId);
    }
}

