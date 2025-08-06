package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.interview.LifestyleHabitsStatus;
import com.my_medi.domain.report.enums.interview.PositiveNegativeStatus;
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
public class Interview {
    @Enumerated(EnumType.STRING)
    private PositiveNegativeStatus hasPastDisease;
    @Enumerated(EnumType.STRING)
    private PositiveNegativeStatus onMedication;
    @Enumerated(EnumType.STRING)
    private LifestyleHabitsStatus lifestyleHabitsStatus;
}