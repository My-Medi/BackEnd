package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalDto;

public interface ProposalCommandService {

    Long writeProposal(Long userId, ProposalDto proposalDto);

    Long editProposal(Long userId, ProposalDto proposalDto);

}
