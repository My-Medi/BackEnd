package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum B8HepatitisStatus {
    POSITIVE("항체 있음"),
    NEGATIVE("항체 없음"),
    SUSPECTED_CARRIER("B형 간염 보유자 의심"),
    UNDETERMINED("판정보류");

    private final String key;
}