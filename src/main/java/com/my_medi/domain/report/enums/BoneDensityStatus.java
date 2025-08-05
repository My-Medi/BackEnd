package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoneDensityStatus {
    NORMAL("정상"),
    OSTEOPENIA("골감소증"),
    OSTEOPOROSIS("골다공증");

    private final String key;
}
