package com.my_medi.domain.report.enums;

import com.my_medi.common.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrineTestStatus implements KeyedEnum {
    NORMAL("정상"),
    BORDERLINE("경계"),
    PROTEINURIA("단백뇨 의심");

    private final String key;
}
