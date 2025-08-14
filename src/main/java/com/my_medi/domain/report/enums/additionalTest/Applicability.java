package com.my_medi.domain.report.enums.additionalTest;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Applicability {
    APPLICABLE("해당"),
    NOT_APPLICABLE("미해당");

    private final String key;
}