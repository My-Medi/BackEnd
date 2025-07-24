package com.my_medi.domain.report.repository;

import com.my_medi.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByUserIdAndRound(Long userId, Integer round);

    Integer countByUserId(Long id);
}
