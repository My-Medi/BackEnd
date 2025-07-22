package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalRequestDto;

public interface ProposalCommandService {

    Long writeProposal(Long userId, ProposalRequestDto proposalRequestDto);

    Long editProposal(Long userId, ProposalRequestDto proposalRequestDto);

}
