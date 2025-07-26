package com.my_medi.domain.schedule.service;


import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return scheduleRepository.findByExpertIdAndDateBetween(expertId, start, end);
    }
}
