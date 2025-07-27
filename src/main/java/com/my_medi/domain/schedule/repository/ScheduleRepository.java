package com.my_medi.domain.schedule.repository;

import com.my_medi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUserId(Long userId);

    List<Schedule> findByExpertId(Long expertId);

    List<Schedule> findByExpertIdAndStartTimeBetween(Long expertId, LocalDateTime start, LocalDateTime end);

    List<Schedule> findByUserIdAndStartTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);

    List<Schedule> findTop3ByExpertIdAndStartTimeAfterOrderByStartTimeAsc(Long expertId, LocalDateTime now);

    List<Schedule> findTop3ByUserIdAndStartTimeAfterOrderByStartTimeAsc(Long userId, LocalDateTime now);



}
