package com.my_medi.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@Builder
public class Reason {
    private HttpStatus httpStatus;
    private final boolean isSuccess;
    private final Integer code;
    private final String message;
    private final HashMap<String, String> result;
}
