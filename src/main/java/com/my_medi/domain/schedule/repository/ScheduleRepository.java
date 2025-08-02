package com.my_medi.domain.schedule.repository;

import com.my_medi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUserId(Long userId);

    List<Schedule> findByExpertId(Long expertId);

    List<Schedule> findByExpertIdAndDateBetween(Long expertId, LocalDate startDate, LocalDate endDate);

    List<Schedule> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Schedule> findTop3ByExpertIdAndDateAfterOrderByDateAscHourAscMinuteAsc(Long expertId, LocalDate now);

    List<Schedule> findTop3ByUserIdAndDateAfterOrderByDateAscHourAscMinuteAsc(Long userId, LocalDate now);

    List<Schedule> findAllByExpertIdAndDate(Long expertId, LocalDate date);



}
