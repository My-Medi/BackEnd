package com.my_medi.domain.expert.entity;
import com.my_medi.common.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Specialty implements KeyedEnum {
    NUTRITIONIST("nutritionist"),//영양사
    HEALTH_MANAGER("manager"), // 건강관리사
    WELLNESS_COACH("coach"), //웰니스코치
    EXERCISE_THERAPIST("therapist"), //운동처방사
    ETC("etc");//기타

    private final String key;
}
