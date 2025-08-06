package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FastingGlucoseStatus {
    NORMAL("정상"),
    DISEASE(""),
    IMPAIRED_FASTING_GLUCOSE("공복혈당장애 의심"),
    DIABETES_MELLITUS("당뇨병 의심");

    private final String key;
}
