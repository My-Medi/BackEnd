package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BloodPressureStatus {
    NORMAL("정상"),
    PREHYPERTENSION("고혈압 전단계"),
    HYPERTENSION("고혈압 의심");

    private final String key;
}
