package com.my_medi.domain.schedule.repository;

import com.my_medi.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUserId(Long userId);

    List<Schedule> findByExpertId(Long expertId);

    List<Schedule> findByExpertIdAndMeetingDateBetween(Long expertId, LocalDate startDate, LocalDate endDate);

    List<Schedule> findByUserIdAndMeetingDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("""
                SELECT s FROM Schedule s
                WHERE s.expert.id = :expertId AND s.meetingDate > :now
                ORDER BY s.meetingDate ASC, s.hour ASC, s.minute ASC
            """)
    List<Schedule> findUpcomingSchedulesByExpert(
            @Param("expertId") Long expertId,
            @Param("now") LocalDate now,
            Pageable pageable
    );

//    @Query("""
//    SELECT s FROM Schedule s
//    WHERE s.user.id = :userId AND s.meetingDate > :now
//    ORDER BY s.meetingDate ASC, s.hour ASC, s.minute ASC
//""")
//    List<Schedule> findUpcomingSchedulesByUser(
//            @Param("userId") Long userId,
//            @Param("now") LocalDate now,
//            Pageable pageable
//    );


    List<Schedule> findAllByExpertIdAndMeetingDate(Long expertId, LocalDate meetingDate);

    List<Schedule> findAllByUserIdAndMeetingDate(Long userId, LocalDate meetingDate);

    void deleteAllByUserId(Long userId);
}

