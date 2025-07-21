package com.my_medi.domain.proposal.service;

import com.my_medi.domain.proposal.entity.Proposal;

public interface ProposalQueryService {

    Proposal getProposalByUserId(Long userId);
}
