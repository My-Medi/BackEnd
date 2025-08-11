package com.my_medi.domain.proposal.service;

import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.exception.ProposalHandler;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProposalQueryServiceImpl implements ProposalQueryService {
    private final ProposalRepository proposalRepository;

    @Override
    public Proposal getProposalByUserId(Long userId) {
        return proposalRepository.findByUserId(userId)
                .orElseThrow(() -> ProposalHandler.NOT_FOUND); //제안서는 필수
    }
}
