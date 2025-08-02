package com.my_medi.domain.schedule.service;

import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.schedule.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleQueryService {

    List<Schedule> getUserSchedules(Long userId);

    List<Schedule> getExpertSchedules(Long expertId);

    List<Schedule> getExpertSchedulesByMonth(Long expertId, int year, int month);

    List<Schedule> getUserSchedulesByMonth(Long userId, int year, int month);

    List<Schedule> getUpcomingSchedulesForUser(Long userId);

    List<Schedule> getUpcomingSchedulesForExpert(Long expertId);

    List<Schedule> getSchedulesByExpertAndDate(Long expertId, LocalDate date);




}
