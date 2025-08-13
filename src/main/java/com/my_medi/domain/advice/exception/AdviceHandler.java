package com.my_medi.domain.advice.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class AdviceHandler extends GeneralException {
    public static final GeneralException NOT_FOUND
            = new AdviceHandler(AdviceErrorStatus.ADVICE_NOT_FOUND);

    public static final GeneralException FORBIDDEN
            = new AdviceHandler(AdviceErrorStatus.ADVICE_UNAUTHORIZED);

    public AdviceHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
