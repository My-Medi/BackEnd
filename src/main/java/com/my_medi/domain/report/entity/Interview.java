package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Interview {
    private Boolean hasPastDisease;
    private Boolean onMedication;
    private Boolean needsSmokingCessation;
    private Boolean needsAlcoholRestriction;
    private Boolean needsExercise;
    private Boolean needsMuscleExercise;
}