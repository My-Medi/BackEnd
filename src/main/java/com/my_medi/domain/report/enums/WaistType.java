package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WaistType {
    NORMAL("정상"),
    ABDOMINAL_OBESITY("복부비만");

    private final String key;
}
