package com.my_medi.api.license.mapper;

import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import org.springframework.stereotype.Component;

@Component
public class LicenseConverter {

    public static License toEntity(LicenseRequestDto dto, Expert expert) {
        License license = License.builder()
                .licenseName(dto.getLicenseName())
                .licenseDate(dto.getLicenseDate())
                .licenseDescription(dto.getLicenseDescription())
                .expert(expert)
                .build();
        return license;
    }
}

