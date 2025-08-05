package com.my_medi.domain.license.entity;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 자격증명
    private String licenseName;
    // 자격증 발급일
    private LocalDate licenseDate;
    // 자격증 내용
    private String licenseDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    public void update(LicenseRequestDto dto) {
        this.licenseName = dto.getLicenseName();
        this.licenseDate = dto.getLicenseDate();
        this.licenseDescription = dto.getLicenseDescription();
    }

}
