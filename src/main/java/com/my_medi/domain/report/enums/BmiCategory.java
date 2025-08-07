package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BmiCategory {
    UNDERWEIGHT("저체중"),
    NORMAL("정상"),
    OVERWEIGHT("과체중"),
    OBESE("비만");

    private final String key;
}