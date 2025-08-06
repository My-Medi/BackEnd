package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FallRiskStatus {
    NORMAL("정상"),
    HIGH_RISK("낙상 고위험자");

    private final String key;
}