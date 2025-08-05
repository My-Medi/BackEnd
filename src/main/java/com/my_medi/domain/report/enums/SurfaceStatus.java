package com.my_medi.domain.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SurfaceStatus {
    NORMAL("정상"),
    PRECISION("정밀");

    private final String key;
}
