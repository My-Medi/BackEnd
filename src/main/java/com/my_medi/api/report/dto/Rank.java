package com.my_medi.api.report.dto;

import com.my_medi.common.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rank implements KeyedEnum {
    UPPER("상위"), LOWER("하위"), NULL("비교불가");

    private final String key;
}