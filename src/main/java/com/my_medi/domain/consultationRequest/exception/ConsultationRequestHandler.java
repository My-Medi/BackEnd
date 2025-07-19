package com.my_medi.domain.consultationRequest.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ConsultationRequestHandler extends GeneralException {
    public static final GeneralException NOT_FOUND =
            new ConsultationRequestHandler(ConsultationRequestErrorStatus.CONSULTATION_REQUEST_NOT_FOUND);

    public ConsultationRequestHandler(BaseErrorCode code) {
        super(code);
    }
}
