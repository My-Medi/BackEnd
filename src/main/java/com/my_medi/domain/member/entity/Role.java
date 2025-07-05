package com.my_medi.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER", "type_user"), EXPERT("ROLE_EXPERT", "type_expert");

    private final String key; // Spring Security용 권한 키
    private final String discriminator; // JPA 상속용 구분 값
}
