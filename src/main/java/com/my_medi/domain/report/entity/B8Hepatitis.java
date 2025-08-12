package com.my_medi.domain.report.entity;

import com.my_medi.api.report.dto.ReportPartitionRequestDto;
import com.my_medi.domain.report.enums.additionalTest.Applicability;
import com.my_medi.domain.report.enums.additionalTest.B8HepatitisStatus;
import com.my_medi.domain.report.enums.additionalTest.SurfaceStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//TODO embed 객체는 패키지 하나 더 추가해 모아두기
@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class B8Hepatitis {
    @Enumerated(EnumType.STRING)
    private Applicability b8hepatitis_applicability;
    private SurfaceStatus surfaceAntigen;
    private SurfaceStatus surfaceAntibody;

    @Enumerated(EnumType.STRING)
    private B8HepatitisStatus b8HepatitisStatus;

    public static B8Hepatitis selectApplicability(ReportPartitionRequestDto.AdditionalTestDto additionalTestDto) {
        if (additionalTestDto.getB8Hepatitis().getB8hepatitis_applicability().equals(Applicability.NOT_APPLICABLE)) {
            return B8Hepatitis.builder()
                    .b8hepatitis_applicability(Applicability.NOT_APPLICABLE)
                    .b8HepatitisStatus(null)
                    .surfaceAntigen(null)
                    .surfaceAntibody(null)
                    .build();
        }else{
            return additionalTestDto.getB8Hepatitis();
        }
    }
}
