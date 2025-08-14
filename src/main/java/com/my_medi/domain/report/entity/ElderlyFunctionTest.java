package com.my_medi.domain.report.entity;

import com.my_medi.api.report.dto.ReportPartitionRequestDto;
import com.my_medi.domain.report.enums.additionalTest.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElderlyFunctionTest {

    @Enumerated(EnumType.STRING)
    private Applicability elderlyFunctionTest_applicability;
    @Enumerated(EnumType.STRING)
    private FallRiskStatus fallRiskStatus;

    @Enumerated(EnumType.STRING)
    private DailyLifeStatus dailyLifeStatus;

    @Enumerated(EnumType.STRING)
    private VaccinationStatus vaccinationStatus;

    @Enumerated(EnumType.STRING)
    private UrinationDisorderStatus urinationDisorderStatus;

    public static ElderlyFunctionTest selectApplicability(ReportPartitionRequestDto.AdditionalTestDto additionalTestDto) {
        if (additionalTestDto.getElderlyFunctionTest().getElderlyFunctionTest_applicability().equals(Applicability.NOT_APPLICABLE)) {
            return ElderlyFunctionTest.builder()
                    .elderlyFunctionTest_applicability(Applicability.NOT_APPLICABLE)
                    .dailyLifeStatus(null)
                    .fallRiskStatus(null)
                    .vaccinationStatus(null)
                    .urinationDisorderStatus(null)
                    .build();
        }else{
            return additionalTestDto.getElderlyFunctionTest();
        }
    }
}
