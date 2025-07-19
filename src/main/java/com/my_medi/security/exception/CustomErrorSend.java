package com.my_medi.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.exception.ErrorStatus;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomErrorSend {
    public static void handleException(HttpServletResponse response, SecurityErrorStatus errorStatus, String errorPoint) throws IOException {
        ApiResponseDto<Object> apiResponseEntity = ApiResponseDto.onFailure(errorStatus.getCode(), errorStatus.getMessage(), errorPoint);

        response.setStatus(errorStatus.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponseEntity));
    }
}
