package com.my_medi.api.licenseImage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LicenseImageRequestDto {
    private String imageUrl;
    private String imageTitle;
}
