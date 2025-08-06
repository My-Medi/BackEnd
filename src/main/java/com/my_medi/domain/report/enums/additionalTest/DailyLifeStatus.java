package com.my_medi.domain.report.enums.additionalTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DailyLifeStatus {
    NORMAL("정상"),
    NEEDS_ASSISTANCE("일상생활 도움 필요");

    private final String key;
}
