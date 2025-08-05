package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.my_medi.domain.report.enums.*;

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
