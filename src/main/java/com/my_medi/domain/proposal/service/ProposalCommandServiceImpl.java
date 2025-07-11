package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.WriteProposalDto;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalCommandServiceImpl implements ProposalCommandService {
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    public Long writeProposal(Long userId, WriteProposalDto writeProposalDto) {
        User user = userRepository.findById(userId).get();
        Proposal proposal = Proposal.create(user, writeProposalDto);
        proposalRepository.save(proposal);
        return proposal.getId();
    }

    public Long editProposal(Long userId, WriteProposalDto writeProposalDto) {
        Proposal proposal = proposalRepository.findByUserId(userId);
        proposal.update(writeProposalDto);
        return proposal.getId();
    }
}
