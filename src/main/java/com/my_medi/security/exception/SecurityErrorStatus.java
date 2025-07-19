package com.my_medi.security.exception;

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
public enum SecurityErrorStatus implements BaseErrorCode {

    //인증 관련 오류(4200~4249)
    AUTH_INVALID_TOKEN(HttpStatus.BAD_REQUEST, 4350, "유효하지 않은 토큰입니다."),
    AUTH_INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, 4351, "유효하지 않은 리프레시 토큰입니다."),
    AUTH_TOKEN_HAS_EXPIRED(HttpStatus.BAD_REQUEST, 4352, "토큰의 유효기간이 만료되었습니다."),
    AUTH_TOKEN_IS_UNSUPPORTED(HttpStatus.BAD_REQUEST, 4353, "서버에서 지원하지 않는 토큰 형식입니다."),
    AUTH_IS_NULL(HttpStatus.BAD_REQUEST, 4354, "토큰 값이 존재하지 않습니다."),
    AUTH_OAUTH2_EMAIL_NOT_FOUND_FROM_PROVIDER(HttpStatus.NOT_FOUND, 4355, "카카오 이메일이 존재하지 않습니다."),
    AUTH_MUST_AUTHORIZED_URI(HttpStatus.BAD_REQUEST, 4356, "인증이 필요한 URI입니다."),
    AUTH_ROLE_CANNOT_EXECUTE_URI(HttpStatus.BAD_REQUEST, 4357, "해당 권한으로는 요청을 처리할 수 없습니다.");

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
