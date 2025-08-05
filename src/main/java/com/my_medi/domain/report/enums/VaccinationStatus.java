package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VaccinationStatus {
    NEEDS_INFLUENZA("인플루엔자 접종 필요"),
    NEEDS_PNEUMOCOCCAL("폐렴구균 접종 필요"),
    NO_NEED("접종 필요 없음");

    private final String key;
}