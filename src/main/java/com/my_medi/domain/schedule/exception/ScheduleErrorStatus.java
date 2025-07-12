package com.my_medi.domain.schedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScheduleErrorStatus {

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    EXPERT_NOT_FOUND(HttpStatus.NOT_FOUND, "전문가를 찾을 수 없습니다."),
    SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT(HttpStatus.FORBIDDEN, "해당 일정은 해당 전문가만 수정 또는 삭제할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
