package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RenalFunctionStatus {
    NORMAL("정상"),
    RENAL_FUNCTION_IMPAIRMENT("신장기능 이상 의심");

    private final String key;
}
