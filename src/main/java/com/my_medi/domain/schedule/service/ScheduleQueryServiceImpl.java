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

        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        return scheduleRepository.findByExpertIdAndStartTimeBetween(expertId, start, end);
    }

    @Override
    public List<Schedule> getUserSchedulesByMonth(Long userId, int year, int month){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        LocalDateTime start = startDate.atTime(23, 59, 59);
        LocalDateTime end = endDate.atTime(23, 59, 59);

        return scheduleRepository.findByUserIdAndStartTimeBetween(userId, start, end);
    }

}
