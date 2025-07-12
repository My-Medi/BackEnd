package com.my_medi.domain.proposal.exception;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;

public class ProposalHandler extends GeneralException {
    public ProposalHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
