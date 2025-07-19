package com.my_medi.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(SecurityErrorStatus errorStatus) {
        super(errorStatus.name());
    }
}
