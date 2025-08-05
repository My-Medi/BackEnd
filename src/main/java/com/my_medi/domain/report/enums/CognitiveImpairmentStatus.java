package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CognitiveImpairmentStatus {
    NO_ABNORMALITY("특이소견 없음"),
    IMPAIRMENT_SUSPECTED("인지기능 저하 의심");

    private final String key;
}

