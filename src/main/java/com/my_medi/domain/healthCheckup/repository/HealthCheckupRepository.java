package com.my_medi.domain.healthCheckup.repository;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.repository.querydsl.HealthCheckupStatsQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HealthCheckupRepository extends JpaRepository<HealthCheckup, Long>, HealthCheckupStatsQueryRepository {
    Optional<HealthCheckup> findBySubscriberIdAndYear(String subscriberId, Integer year);

    @Query("SELECT COUNT(h) FROM HealthCheckup h WHERE h.year = :year")
    Long countByYear(@Param("year") Integer year);

    boolean existsBySubscriberIdAndYear(String subscriberId, Integer year);

    @Query("SELECT h FROM HealthCheckup h WHERE h.ageGroup5yr IN :ageGroups AND h.gender = :gender")
    List<HealthCheckup> findByAgeGroupsAndGender(@Param("ageGroups") List<Integer> ageGroups,
                                                 @Param("gender") String gender);
}
