package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.domain.user.entity.User;

public interface ProposalCommandService {

    Long writeProposal(User user, ProposalRequestDto proposalRequestDto);

    Long editProposal(User user, ProposalRequestDto proposalRequestDto);

}
