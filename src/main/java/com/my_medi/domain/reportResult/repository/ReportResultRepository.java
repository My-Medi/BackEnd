package com.my_medi.domain.reportResult.repository;

import com.my_medi.domain.reportResult.entity.ReportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReportResultRepository extends JpaRepository<ReportResult, Long> {
    boolean existsByReportId(Long reportId);

    void deleteByReportId(Long reportId);

    Optional<ReportResult> findByReportId(Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
      delete from ReportResult rr
      where rr.report.id in (
        select r.id from Report r where r.user.id = :userId
      )
    """)
    void deleteAllByUserId(@Param("userId") Long userId);
}
