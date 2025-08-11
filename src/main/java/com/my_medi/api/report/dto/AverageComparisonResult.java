package com.my_medi.api.report.dto;

public enum AverageComparisonResult {
    ABOVE_GOOD,   // 평균보다 좋음
    SIMILAR,      // 평균과 유사
    BELOW_BAD,    // 평균보다 나쁨
    INSUFFICIENT  // 판단 불가(데이터 부족/UNKNOWN)
}
