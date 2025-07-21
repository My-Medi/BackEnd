package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterviewDto {
    private Boolean hasPastDisease;
    private Boolean onMedication;
    private Boolean needsSmokingCessation;
    private Boolean needsAlcoholRestriction;
    private Boolean needsExercise;
    private Boolean needsMuscleExercise;
}
