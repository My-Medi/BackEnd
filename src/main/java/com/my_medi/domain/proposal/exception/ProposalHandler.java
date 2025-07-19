package com.my_medi.domain.proposal.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.expert.exception.ExpertErrorStatus;
import com.my_medi.domain.expert.exception.ExpertHandler;

public class ProposalHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ProposalHandler(ProposalErrorStatus.PROPOSAL_NOT_FOUND);
    /**
     * Constructs a new ProposalHandler exception with the specified error code.
     *
     * @param baseErrorCode the error code representing the specific proposal-related error
     */
    public ProposalHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
