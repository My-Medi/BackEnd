package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrinationDisorderStatus {
    NORMAL("정상"),
    SUSPECTED("배뇨장애 의심");

    private final String key;
}
