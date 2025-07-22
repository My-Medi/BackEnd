package com.my_medi.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {
    private Boolean hasPastDisease;
    private Boolean onMedication;
    private Boolean needsSmokingCessation;
    private Boolean needsAlcoholRestriction;
    private Boolean needsExercise;
    private Boolean needsMuscleExercise;
}
