package com.my_medi.domain.consultationRequest.exception;

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
public enum ConsultationRequestErrorStatus implements BaseErrorCode {

    // consultationRequest (4150–4199)
    CONSULTATION_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, 4150, "상담 요청이 존재하지 않습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 4151, "사용자를 찾을 수 없습니다."),

    EXPERT_NOT_FOUND(HttpStatus.NOT_FOUND, 4152, "전문가를 찾을 수 없습니다."),

    REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER(HttpStatus.FORBIDDEN, 4153, "해당 상담 요청은 해당 사용자만 수정 또는 취소할 수 있습니다."),

    INVALID_REQUEST_STATUS(HttpStatus.BAD_REQUEST, 4154, "요청 상태가 유효하지 않아 작업을 수행할 수 없습니다.");


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
