package com.my_medi.domain.proposal.service;

import com.my_medi.common.exception.ErrorStatus;
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

    /**
     * Retrieves the proposal associated with the specified user ID.
     *
     * @param userId the ID of the user whose proposal is to be retrieved
     * @return the Proposal entity linked to the given user ID
     * @throws ProposalHandler.NOT_FOUND if no proposal is found for the specified user ID
     */
    @Override
    public Proposal getProposalByUserId(Long userId) {
        return proposalRepository.findByUserId(userId)
                .orElseThrow(() -> ProposalHandler.NOT_FOUND);
    }
}
