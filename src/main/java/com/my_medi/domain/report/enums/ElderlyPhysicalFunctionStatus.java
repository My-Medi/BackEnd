package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElderlyPhysicalFunctionStatus {
    NORMAL("정상"),
    DECLINED("신체기능저하");

    private final String key;
}
