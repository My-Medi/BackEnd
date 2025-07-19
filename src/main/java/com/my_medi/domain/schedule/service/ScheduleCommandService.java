package com.my_medi.domain.schedule.service;

import com.my_medi.api.schedule.dto.EditScheduleDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;

public interface ScheduleCommandService {
    Long registerScheduleToUser(Long expertId, Long userId, RegisterScheduleDto registerScheduleDto);

    Long editSchedule(Long expertId, Long scheduleId , EditScheduleDto editScheduleDto);

    Long removeSchedule(Long expertId, Long scheduleId);
}
