package com.my_medi.api.report.dto;

import com.my_medi.common.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AverageComparison implements KeyedEnum {
    VERY_ABOVE("평균보다 매우 높습니다."),
    SLIGHTLY_ABOVE("평균보다 조금 높습니다"),
    SIMILAR("평균과 유사합니다."),
    SLIGHTLY_BELOW("평균보다 조금 낮습니다."),
    VERY_BELOW("평균보다 매우 낮습니다.");

    private final String key;
}
