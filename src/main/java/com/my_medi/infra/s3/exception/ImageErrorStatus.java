package com.my_medi.infra.s3.exception;

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
public enum ImageErrorStatus implements BaseErrorCode {

    IMAGE_REQUEST_IS_EMPTY(HttpStatus.BAD_REQUEST, 4400, "이미지 요청이 비어있습니다"),
    IMAGE_FORM_IS_WRONG(HttpStatus.BAD_REQUEST, 4401, "잘못된 형식의 파일이 요청되었습니다."),
    IMAGE_FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, 4402, "이미지 파일 업로드가 실패하였습니다.");


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
