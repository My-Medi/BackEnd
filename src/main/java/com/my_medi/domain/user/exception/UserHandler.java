package com.my_medi.domain.user.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;

public class UserHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new UserHandler(UserErrorStatus.USER_NOT_FOUND);
    public UserHandler(BaseErrorCode code) {
        super(code);
    }
}
