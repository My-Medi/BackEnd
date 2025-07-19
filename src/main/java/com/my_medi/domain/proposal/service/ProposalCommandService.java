package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalDto;

public interface ProposalCommandService {

    /**
 * Creates a new proposal for the specified user.
 *
 * @param userId the identifier of the user creating the proposal
 * @param proposalDto the data transfer object containing proposal details
 * @return the identifier of the newly created proposal
 */
Long writeProposal(Long userId, ProposalDto proposalDto);

    /**
 * Updates an existing proposal for the specified user.
 *
 * @param userId the identifier of the user modifying the proposal
 * @param proposalDto the data transfer object containing updated proposal information
 * @return the identifier of the updated proposal
 */
Long editProposal(Long userId, ProposalDto proposalDto);

}
