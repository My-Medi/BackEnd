package com.my_medi.domain.consultationRequest.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ConsultationRequestHandler extends GeneralException {
    public static final GeneralException NOT_FOUND =
            new ConsultationRequestHandler(ConsultationRequestErrorStatus.CONSULTATION_REQUEST_NOT_FOUND);

    public static final GeneralException INVALID_STATUS = new ConsultationRequestHandler(ConsultationRequestErrorStatus.INVALID_STATUS);

    public static final GeneralException FORBIDDEN_REQUEST_OWNER_MISMATCH = new ConsultationRequestHandler(ConsultationRequestErrorStatus.REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER);

    public static final GeneralException REQUEST_STATUS_MISMATCH = new ConsultationRequestHandler(ConsultationRequestErrorStatus.STATUS_MISMATCH);

    public static final GeneralException CONSULTATION_LIMIT_EXCEEDED = new ConsultationRequestHandler(ConsultationRequestErrorStatus.CONSULTATION_LIMIT_EXCEEDED);

    public static final GeneralException ALREADY_PROCESSED_CONSULTATION = new ConsultationRequestHandler(ConsultationRequestErrorStatus.ALREADY_PROCESSED_CONSULTATION);

    public static final GeneralException REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER = new ConsultationRequestHandler(ConsultationRequestErrorStatus.REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER);

    public static final GeneralException EXPERT_MISMATCH = new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_MISMATCH);

    public static final GeneralException DUPLICATED_CONSULTATION = new ConsultationRequestHandler(ConsultationRequestErrorStatus.DUPLICATED_CONSULTATION);

    public static final GeneralException INVALID_REQUEST_STATUS = new ConsultationRequestHandler(ConsultationRequestErrorStatus.INVALID_REQUEST_STATUS);








    public ConsultationRequestHandler(BaseErrorCode code) {
        super(code);
    }
}
