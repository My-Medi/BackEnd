package com.my_medi.api.licenseImage.mapper;

import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import org.springframework.stereotype.Component;

@Component
public class LicenseImageConverter {

    public static LicenseImage toEntity(LicenseImageRequestDto dto, Expert expert) {
        return LicenseImage.builder()
                .imageUrl(dto.getImageUrl())
                .imageTitle(dto.getImageTitle())
                //.expert(expert)
                .build();
    }
}

