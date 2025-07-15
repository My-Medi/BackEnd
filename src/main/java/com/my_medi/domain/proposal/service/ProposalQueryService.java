package com.my_medi.domain.proposal.service;

import com.my_medi.domain.proposal.entity.Proposal;
import java.util.Optional;

public interface ProposalQueryService {

    Proposal getProposalByUserId(Long userId);
}
