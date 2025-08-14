package com.my_medi.domain.report.repository;

import com.my_medi.api.report.dto.UserLatestReportStatusDto;
import com.my_medi.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByUserIdAndRound(Long userId, Integer round);

    Integer countByUserId(Long id);

    Optional<Report> findTopByUserIdOrderByRoundDesc(Long userId);

    @Query("""
        SELECT new com.my_medi.api.report.dto.UserLatestReportStatusDto(r.user.id, r.id, rr.totalHealthStatus)
        FROM Report r
        JOIN ReportResult rr ON rr.report = r
        WHERE r.user.id IN :userIds
          AND r.checkupDate = (
              SELECT MAX(r2.checkupDate)
              FROM Report r2
              WHERE r2.user.id = r.user.id
          )
    """)
    List<UserLatestReportStatusDto> findLatestReportStatusByUserIds(@Param("userIds") Set<Long> userIds);
}
