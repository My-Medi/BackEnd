package com.my_medi.security.exception;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public static final AuthenticationException UNAUTHORIZED_LOGIN_DATA
            = new JwtAuthenticationException(SecurityErrorStatus.AUTH_UNAUTHORIZED_LOGIN_DATA_RETRIEVAL_ERROR);
    public static final AuthenticationException ASSIGNABLE_PARAMETER
            = new JwtAuthenticationException(SecurityErrorStatus.AUTH_ASSIGNABLE_PARAMETER);

    public static final AuthenticationException WRONG_PASSWORD
            = new JwtAuthenticationException(SecurityErrorStatus.AUTH_WRONG_PASSWORD);
    public JwtAuthenticationException(SecurityErrorStatus errorStatus) {
        super(errorStatus.name());
    }
}
