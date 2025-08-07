package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImagingTestStatus {
    NORMAL("정상"),
    INACTIVE_PULMONARY_TUBERCULOSIS("비활동성 폐결핵"),
    DISEASE("질환 의심"),
    OTHERS("기타");

    private final String key;
}
