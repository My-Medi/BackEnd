package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.WriteProposalDto;

public interface ProposalCommandService {

    Long writeProposal(Long userId, WriteProposalDto writeProposalDto);

    Long editProposal(Long userId, WriteProposalDto writeProposalDto);

}
