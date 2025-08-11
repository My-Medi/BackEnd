package com.my_medi.api.report.dto;

import com.my_medi.common.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HealthStatus implements KeyedEnum {
    SAFE("안심", 0),
    NORMAL("정상", 1),
    WATCH("관심",2),
    CAUTION("주의",3),
    DANGER("위험",4),
    UNKNOWN("미정",99);

    private final String key;
    private final int severity;
}
