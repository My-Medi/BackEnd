package com.my_medi.domain.expertNotification.exception;

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
public enum ExpertNotificationErrorStatus implements BaseErrorCode {
    EXPERT_NOTIFICATION_NOT_FOUND(HttpStatus.BAD_REQUEST, 4200, "해당 notification은 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    /**
     * Constructs a {@link Reason} object representing this error status, including the error message, code, and a success flag set to false.
     *
     * @return a {@link Reason} object describing the error.
     */
    @Override
    public Reason getReason() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    /**
     * Constructs a {@link Reason} object containing the error message, code, HTTP status, and a success flag set to false.
     *
     * @return a {@link Reason} instance representing this error status with HTTP status included
     */
    @Override
    public Reason getReasonHttpStatus() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

    /**
     * Retrieves the explanation for the error from the {@link ExplainError} annotation on the enum constant, if present.
     * If the annotation is not found, returns the error message.
     *
     * @return the explanation for the error or the default error message if no annotation is present
     * @throws NoSuchFieldException if the enum constant field cannot be found
     */
    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }
}
