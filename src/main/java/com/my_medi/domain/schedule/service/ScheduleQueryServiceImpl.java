package com.my_medi.domain.schedule.service;


import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDate;
import java.util.Collections;
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

        return scheduleRepository.findByExpertIdAndMeetingDateBetween(expertId, startDate, endDate);
    }

    @Override
    public List<Schedule> getUserSchedulesByMonth(Long userId, int year, int month){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return scheduleRepository.findByUserIdAndMeetingDateBetween(userId, startDate, endDate);
    }

    private List<Schedule> findUpcomingSchedules(SortType type, Long id) {
        LocalDate now = LocalDate.now();
        Pageable top3 = PageRequest.of(0, 3);
        Pageable top1 = PageRequest.of(0, 1);

        return switch (type) {
//            case USER -> scheduleRepository.findUpcomingSchedulesByUser(id, now, top3);
            case USER -> Collections.emptyList();
            case EXPERT -> scheduleRepository.findUpcomingSchedulesByExpert(id, now, top1);
        };
    }

//    @Override
//    public List<Schedule> getUpcomingSchedulesForUser(Long userId) {
//        return findUpcomingSchedules(SortType.USER, userId);
//    }

    @Override
    public List<Schedule> getUpcomingSchedulesForExpert(Long expertId) {
        return findUpcomingSchedules(SortType.EXPERT, expertId);
    }

    public enum SortType { USER, EXPERT }

    public List<Schedule> getSchedulesByExpertAndDate(Long expertId, LocalDate meetingDate) {
        return scheduleRepository.findAllByExpertIdAndMeetingDate(expertId, meetingDate);
    }

    public List<Schedule> getSchedulesByUserAndDate(Long userId, LocalDate meetingDate) {
        return scheduleRepository.findAllByUserIdAndMeetingDate(userId, meetingDate);
    }



}
