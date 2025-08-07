package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DepressionStatus {
    NO_SYMPTOMS("우울증상이 없음"),
    MILD("가벼운 우울증상"),
    MODERATE_SUSPECTED("중간 정도 우울증 의심"),
    SEVERE_SUSPECTED("심한 우울증 의심");

    private final String key;
}

