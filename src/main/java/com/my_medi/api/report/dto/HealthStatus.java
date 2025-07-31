package com.my_medi.api.report.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HealthStatus {
    RELIEVED("안심"),
    NORMAL("정상"),
    ATTENTION("관심"),
    CAUTION("주의"),
    DANGER("위험");

    private final String label;
}
