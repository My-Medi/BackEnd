package com.my_medi.domain.expert.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ExpertHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ExpertHandler(ExpertErrorStatus.EXPERT_NOT_FOUND);

    public ExpertHandler(BaseErrorCode code) {
        super(code);
    }
}

