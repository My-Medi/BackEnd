package com.my_medi.domain.userNotification.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationMessage {
    MATCH_COMPLETE(" %s님과 첫 매칭이 완료되었습니다! 마이홈에서 확인해보세요."),
    SCHEDULE_REGISTERED("상담예약일이 공유캘린더에 등록되었습니다. 캘린더를 확인해보세요!"),
    FORM_APPROVED("건강관리요청서가 수락되었습니다.");

    private final String template;
}
