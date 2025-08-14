package com.my_medi.domain.reportResult.repository;

import com.my_medi.domain.reportResult.entity.ReportResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportResultRepository extends JpaRepository<ReportResult, Long> {
    boolean existsByReportId(Long reportId);

    void deleteByReportId(Long reportId);

    Optional<ReportResult> findByReportId(Long id);
}
