package com.my_medi.domain.userNotification.exception;

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
public enum UserNotificationErrorStatus implements BaseErrorCode {

    USER_NOTIFICATION_NOT_FOUND(HttpStatus.BAD_REQUEST, 4250, "user 알림을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    /**
     * Constructs a {@link Reason} object representing this error status, including the error message, code, and a success flag set to false.
     *
     * @return a {@link Reason} object describing the error without HTTP status information
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
     * Constructs a {@link Reason} object containing the error message, code, HTTP status, and a failure flag.
     *
     * @return a {@link Reason} instance representing this error status, including the associated HTTP status.
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
     * Retrieves the explanation for the error, using the value from the {@link ExplainError} annotation if present;
     * otherwise, returns the default error message.
     *
     * @return the explanation for the error
     * @throws NoSuchFieldException if the enum constant field cannot be found via reflection
     */
    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }
}
