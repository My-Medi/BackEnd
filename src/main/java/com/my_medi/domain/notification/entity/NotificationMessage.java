package com.my_medi.domain.notification.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationMessage {
    // User
    MATCH_COMPLETE(" %s님과 첫 매칭이 완료되었습니다! 마이홈에서 확인해보세요."),
    SCHEDULE_REGISTERED("상담예약일이 공유캘린더에 등록되었습니다. 캘린더를 확인해보세요!"),
    CONSULTATION_APPROVED("s%님께서 건강관리요청서를 수락하셨습니다."),

    // Expert
    CONSULTATION_REQUESTED("%s님께서 건강관리를 요청하셨습니다.");

    private final String template;

    public String format(Object... args) {
        return String.format(template, args);
    }
}
