package com.my_medi.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("1"),   // 남자
    FEMALE("2");  // 여자

    private final String key;
}
