package com.my_medi.domain.licenseImage.entity;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.licenseImage.dto.LicenseImageRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.license.entity.License;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LicenseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 이미지 파일 URL
    private String imageUrl;
    // 이미지 제목
    private String imageTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    public void update(LicenseImageRequestDto dto) {
        this.imageUrl = dto.getImageUrl();
        this.imageTitle = dto.getImageTitle();
    }

}
