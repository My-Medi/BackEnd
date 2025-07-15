package com.my_medi.domain.proposal.repository;

import com.my_medi.domain.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findByUserId(Long userId);
}
