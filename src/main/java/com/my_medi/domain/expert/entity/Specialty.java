package com.my_medi.domain.expert.entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Specialty {
    NUTRITIONIST,//영양사
    HEALTH_MANAGER, // 건강관리사
    WELLNESS_COACH, //웰니스코치
    EXERCISE_THERAPIST, //운동처방사
    ETC;//기타
}
