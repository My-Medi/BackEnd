package com.my_medi.domain.proposal.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ProposalHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ProposalHandler(ProposalErrorStatus.PROPOSAL_NOT_FOUND);
    public ProposalHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
