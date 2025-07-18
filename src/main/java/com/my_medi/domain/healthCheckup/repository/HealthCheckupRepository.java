package com.my_medi.domain.healthCheckup.repository;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HealthCheckupRepository extends JpaRepository<HealthCheckup, Long> {
    Optional<HealthCheckup> findBySubscriberIdAndYear(String subscriberId, Integer year);

    @Query("SELECT COUNT(h) FROM HealthCheckup h WHERE h.year = :year")
    Long countByYear(@Param("year") Integer year);

    boolean existsBySubscriberIdAndYear(String subscriberId, Integer year);
}
