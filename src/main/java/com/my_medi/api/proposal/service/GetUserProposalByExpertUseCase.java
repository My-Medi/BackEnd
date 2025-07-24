package com.my_medi.api.proposal.service;

import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.api.proposal.validator.UserProposalForExpertValidator;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProposalByExpertUseCase {
    private final ProposalQueryService proposalQueryService;
    private final UserProposalForExpertValidator UserProposalForExpertValidator;

    public ProposalResponseDto.UserProposalDto getUserProposalForExpert(Expert expert, Long userId) {
        UserProposalForExpertValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        Proposal proposal = proposalQueryService.getProposalByUserId(userId);

        return ProposalConverter.toUserProposalDto(proposal);
    }
}

