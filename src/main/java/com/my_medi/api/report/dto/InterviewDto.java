package com.my_medi.api.report.dto;

import com.my_medi.domain.report.enums.interview.LifestyleHabitsStatus;
import com.my_medi.domain.report.enums.interview.PositiveNegativeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {
    private PositiveNegativeStatus hasPastDisease;

    private PositiveNegativeStatus onMedication;

    private LifestyleHabitsStatus lifestyleHabitsStatus;
}
