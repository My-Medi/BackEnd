package com.my_medi.domain.schedule.exception;


import com.my_medi.common.annotation.ExplainError;
import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.Reason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ScheduleErrorStatus implements BaseErrorCode {

    // entity SCHEDULE (4100-4149)
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, 4100, "일정이 존재하지 않습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 4101, "사용자를 찾을 수 없습니다."),

    EXPERT_NOT_FOUND(HttpStatus.NOT_FOUND, 4102, "전문가를 찾을 수 없습니다."),

    SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT(HttpStatus.FORBIDDEN, 4103, "해당 일정은 해당 전문가만 수정 또는 삭제할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    @Override
    public Reason getReason() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public Reason getReasonHttpStatus() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }
}
