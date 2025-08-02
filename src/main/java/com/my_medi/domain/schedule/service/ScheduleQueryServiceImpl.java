package com.my_medi.domain.schedule.service;


import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getUserSchedules(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }

    @Override
    public List<Schedule> getExpertSchedules(Long expertId) {
        return scheduleRepository.findByExpertId(expertId);
    }

    @Override
    public List<Schedule> getExpertSchedulesByMonth(Long expertId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return scheduleRepository.findByExpertIdAndDateBetween(expertId, startDate, endDate);
    }

    @Override
    public List<Schedule> getUserSchedulesByMonth(Long userId, int year, int month){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return scheduleRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    private List<Schedule> findUpcomingSchedules(SortType type, Long id) {
        LocalDate now = LocalDate.now();

        return switch (type) {
            case USER -> scheduleRepository.findTop3ByUserIdAndDateAfterOrderByDateAscHourAscMinuteAsc(id, now);
            case EXPERT -> scheduleRepository.findTop3ByExpertIdAndDateAfterOrderByDateAscHourAscMinuteAsc(id, now);
        };
    }

    @Override
    public List<Schedule> getUpcomingSchedulesForUser(Long userId) {
        return findUpcomingSchedules(SortType.USER, userId);
    }

    @Override
    public List<Schedule> getUpcomingSchedulesForExpert(Long expertId) {
        return findUpcomingSchedules(SortType.EXPERT, expertId);
    }

    public enum SortType { USER, EXPERT }

    public List<Schedule> getSchedulesByExpertAndDate(Long expertId, LocalDate date) {
        return scheduleRepository.findAllByExpertIdAndDate(expertId, date);
    }


}
