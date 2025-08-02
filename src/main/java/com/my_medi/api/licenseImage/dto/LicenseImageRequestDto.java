package com.my_medi.api.licenseImage.dto;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LicenseImageRequestDto {
    private String imageUrl;
    private String imageTitle;

    public LicenseImage toEntity(Expert expert) {
        return LicenseImage.builder()
                .expert(expert)
                .imageUrl(imageUrl)
                .imageTitle(imageTitle)
                .build();
    }
}
