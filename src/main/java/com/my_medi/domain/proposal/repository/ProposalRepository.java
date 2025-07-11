package com.my_medi.domain.proposal.repository;

import com.my_medi.domain.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Proposal findByUserId(Long userId);
}
