package com.my_medi.api.common.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.my_medi.common.exception.BaseCode;
import com.my_medi.common.exception.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponseDto<T> {
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
    private T result;

    public static <T> ApiResponseDto<T> onSuccess(T result) {
        return new ApiResponseDto<>(true, SuccessStatus._SUCCESS.getReasonHttpStatus().getCode(), SuccessStatus._SUCCESS.getMessage(), result);
    }

    public static <T> ApiResponseDto<T> of(BaseCode code, T result) {
        return new ApiResponseDto<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    public static <T> ApiResponseDto<T> onFailure(Integer code, String message, T data) {
        return new ApiResponseDto<>(false, code, message, data);
    }
}
