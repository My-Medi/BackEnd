package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CognitiveImpairmentStatus {
    NOT_APPLICABLE("미해당"),
    NO_ABNORMALITY("특이소견 없음"),
    IMPAIRMENT_SUSPECTED("인지기능 저하 의심");

    private final String key;
}

