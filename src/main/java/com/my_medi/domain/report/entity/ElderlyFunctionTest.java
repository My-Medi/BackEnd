package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.additionalTest.DailyLifeStatus;
import com.my_medi.domain.report.enums.additionalTest.FallRiskStatus;
import com.my_medi.domain.report.enums.additionalTest.UrinationDisorderStatus;
import com.my_medi.domain.report.enums.additionalTest.VaccinationStatus;
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
    private FallRiskStatus fallRiskStatus;

    @Enumerated(EnumType.STRING)
    private DailyLifeStatus dailyLifeStatus;

    @Enumerated(EnumType.STRING)
    private VaccinationStatus vaccinationStatus;

    @Enumerated(EnumType.STRING)
    private UrinationDisorderStatus urinationDisorderStatus;
}
