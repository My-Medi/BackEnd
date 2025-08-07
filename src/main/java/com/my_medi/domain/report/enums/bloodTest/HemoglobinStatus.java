package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HemoglobinStatus {
    NORMAL("정상"),
    SUSPECTED_ANEMIA("빈혈 의심"),
    OTHERS("기타");

    private final String key;
}
