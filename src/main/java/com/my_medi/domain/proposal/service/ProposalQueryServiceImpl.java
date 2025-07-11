package com.my_medi.domain.proposal.service;

import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProposalQueryServiceImpl implements ProposalQueryService {
    private final ProposalRepository proposalRepository;

    public Proposal getProposalByUserId(Long userId) {
        return proposalRepository.findByUserId(userId);
    }
}
