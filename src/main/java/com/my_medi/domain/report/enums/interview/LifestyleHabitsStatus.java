package com.my_medi.domain.report.enums.interview;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LifestyleHabitsStatus {
    SMOKING_CESSATION_NEEDED("금연 필요"),
    ALCOHOL_REDUCTION_NEEDED("절주 필요"),
    PHYSICAL_ACTIVITY_NEEDED("신체활동 필요"),
    STRENGTH_TRAINING_NEEDED("근력운동 필요");

    private final String key;
}
