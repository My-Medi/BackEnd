package com.my_medi.domain.schedule.service;

import com.my_medi.api.schedule.dto.RegisterScheduleDto;

public interface ScheduleCommandService {
    Long registerScheduleToUser(Long expertId, Long userId, RegisterScheduleDto registerScheduleDto);

    //TODO 수정항목 생각해서 dto 생성한뒤 argument에 추가하기
    Long editSchedule(Long expertId, Long scheduleId);

    Long removeSchedule(Long expertId, Long scheduleId);
}
