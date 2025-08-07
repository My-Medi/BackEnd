package com.my_medi.domain.report.enums.bloodTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HearingStatus {
    NORMAL("정상"),
    SUSPECTED_DISEASE("질환 의심");

    private final String key;
}
