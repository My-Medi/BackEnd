package com.my_medi.domain.expert.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class ExpertHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ExpertHandler(ExpertErrorStatus.EXPERT_NOT_FOUND);
    public static final ExpertHandler EXPERT_EMAIL_NOT_FOUND =
            new ExpertHandler(ExpertErrorStatus.EXPERT_EMAIL_NOT_FOUND);
    public static final ExpertHandler EXPERT_CARRER_NOT_FOUND =
            new ExpertHandler(ExpertErrorStatus.EXPERT_CARRER_NOT_FOUND);
    public static final ExpertHandler EXPERT_LICENSE_NOT_FOUND =
            new ExpertHandler(ExpertErrorStatus.EXPERT_LICENSE_NOT_FOUND);
    public static final ExpertHandler EXPERT_LICENSE_IMAGE_NOT_FOUND =
            new ExpertHandler(ExpertErrorStatus.EXPERT_LICENSE_IMAGE_NOT_FOUND);

    public ExpertHandler(BaseErrorCode code) {
        super(code);
    }
}

