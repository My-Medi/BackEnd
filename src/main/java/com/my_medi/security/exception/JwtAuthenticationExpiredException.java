package com.my_medi.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationExpiredException extends JwtAuthenticationException{

    public static final AuthenticationException EXPIRED =
            new JwtAuthenticationException(SecurityErrorStatus.AUTH_TOKEN_HAS_EXPIRED);

    public JwtAuthenticationExpiredException(SecurityErrorStatus errorStatus) {
        super(errorStatus);
    }
}
