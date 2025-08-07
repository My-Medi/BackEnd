package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LiverFunctionStatus {
    NORMAL("정상"),
    LIVER_FUNCTION_IMPAIRMENT("간기능 이상 의심");

    private final String key;
}
