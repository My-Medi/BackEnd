package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.exception.ProposalHandler;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalCommandServiceImpl implements ProposalCommandService {
    private final ProposalRepository proposalRepository;

    @Override
    public Long writeProposal(User user, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = ProposalConverter.toEntity(user, proposalRequestDto);
        proposalRepository.save(proposal);
        return proposal.getId();
    }

    @Override
    public Long editProposal(User user, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = proposalRepository.findByUserId(user.getId())
                .orElseThrow(() -> ProposalHandler.NOT_FOUND);

        proposal.updateUserDetails(proposalRequestDto);
        proposal.updateHealthInterests(proposalRequestDto);
        proposal.updateAbnormalValue(proposalRequestDto);
        proposal.updateHelpTopic(proposalRequestDto);

        return proposal.getId();
    }
}
