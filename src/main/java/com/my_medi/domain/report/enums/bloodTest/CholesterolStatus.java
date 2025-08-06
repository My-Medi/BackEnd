package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CholesterolStatus {
    NORMAL("정상"),
    HYPER_CHOLESTEROL_EMIA("고콜레스테롤혈증 의심"),
    LOW_HDL_CHOLESTEROL("낮은 HDL 콜레스테롤 의심"),
    DISEASE("유질환자");

    private final String key;
}
