package com.my_medi.domain.advice.repository;

import com.my_medi.domain.advice.entity.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    Page<Advice> findAllByUserId(Long userId, Pageable pageable);
    Page<Advice> findAllByExpertIdAndUserId(Long expertId, Long userId, Pageable pageable);
    Optional<Advice> findTop1ByUserIdOrderByCreatedDateDescIdDesc(Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Advice a where a.expert.id = :expertId")
    void deleteAllByExpertId(@Param("expertId") Long expertId);
}
