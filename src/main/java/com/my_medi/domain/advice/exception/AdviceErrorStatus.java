package com.my_medi.domain.advice.exception;

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
public enum AdviceErrorStatus implements BaseErrorCode {

    // Entity advice (4500~4549)
    ADVICE_NOT_FOUND(HttpStatus.NOT_FOUND, 4500, "advice를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    @Override
    public Reason getReason() {
        return com.my_medi.common.exception.Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public Reason getReasonHttpStatus() {
        return com.my_medi.common.exception.Reason.builder()
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
