package com.my_medi.domain.advice.repository;

import com.my_medi.domain.advice.entity.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdviceRepository extends JpaRepository<Advice, Long> {
    Page<Advice> findAllByUserId(Long userId, Pageable pageable);
    Page<Advice> findAllByExpertId(Long expertId, Pageable pageable);
    Optional<Advice> findTop1ByUserIdOrderByCreatedDateDesc(Long userId);
}
