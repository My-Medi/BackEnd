package com.my_medi.domain.member.entity;

import com.my_medi.common.consts.StaticVariable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("USER", StaticVariable.USER), EXPERT("EXPERT", StaticVariable.EXPERT);

    private final String key; // Spring Security용 권한 키
    private final String discriminator; // JPA 상속용 구분 값

}
