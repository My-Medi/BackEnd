package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;
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
    private Boolean hasPastDisease;
    private Boolean onMedication;
    private Boolean needsSmokingCessation;
    private Boolean needsAlcoholRestriction;
    private Boolean needsExercise;
    private Boolean needsMuscleExercise;
}