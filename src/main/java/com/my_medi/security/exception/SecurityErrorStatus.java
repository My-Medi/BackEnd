package com.my_medi.security.exception;

import org.springframework.http.HttpStatus;

public enum SecurityErrorStatus {

    //인증 관련 오류(4200~4249)
    AUTH_INVALID_TOKEN(HttpStatus.BAD_REQUEST, 4200, "유효하지 않은 토큰입니다."),
    AUTH_INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, 4201, "유효하지 않은 리프레시 토큰입니다."),
    AUTH_TOKEN_HAS_EXPIRED(HttpStatus.BAD_REQUEST, 4202, "토큰의 유효기간이 만료되었습니다."),
    AUTH_TOKEN_IS_UNSUPPORTED(HttpStatus.BAD_REQUEST, 4203, "서버에서 지원하지 않는 토큰 형식입니다."),
    AUTH_IS_NULL(HttpStatus.BAD_REQUEST, 4204, "토큰 값이 존재하지 않습니다."),
    AUTH_OAUTH2_EMAIL_NOT_FOUND_FROM_PROVIDER(HttpStatus.NOT_FOUND, 4205, "카카오 이메일이 존재하지 않습니다."),
    AUTH_MUST_AUTHORIZED_URI(HttpStatus.BAD_REQUEST, 4206, "인증이 필요한 URI입니다."),
    AUTH_ROLE_CANNOT_EXECUTE_URI(HttpStatus.BAD_REQUEST, 4207, "해당 권한으로는 요청을 처리할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    SecurityErrorStatus(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
