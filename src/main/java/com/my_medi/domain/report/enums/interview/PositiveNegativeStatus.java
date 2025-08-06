package com.my_medi.domain.report.enums.interview;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PositiveNegativeStatus {
    POSITIVE("유"),
    NEGATIVE("무");

    private final String key;
}
