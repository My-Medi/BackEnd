package com.my_medi.api.schedule.service;

import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.userNotification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUseCase {
    private final ScheduleCommandService scheduleCommandService;
    private final UserNotificationCommandService userNotificationCommandService;

    public Long registScheduleAndSendNotificationToUser(
            Expert expert, Long userId, RegisterScheduleDto registerScheduleDto) {
        Long scheduleId = scheduleCommandService
                .registerScheduleToUser(expert.getId(), userId, registerScheduleDto);

        userNotificationCommandService.sendScheduleNotificationToUser(userId, scheduleId);

        return scheduleId;
    }
}
