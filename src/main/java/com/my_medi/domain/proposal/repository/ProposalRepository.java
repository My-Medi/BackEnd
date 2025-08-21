package com.my_medi.domain.proposal.repository;

import com.my_medi.domain.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findByUserId(Long userId);

    @Query("""
        select p
        from Proposal p
        join fetch p.user u
        where u.id in :userIds
    """)
    List<Proposal> findAllByUserIdInWithUser(@Param("userIds") Set<Long> userIds);

    void deleteByUserId(Long userId);
}
