package com.my_medi.api.schedule.service;

import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUseCase {
    private final ScheduleCommandService scheduleCommandService;
    private final UserNotificationCommandService userNotificationCommandService;

    public Long registerScheduleAndSendNotificationToUser(
            Expert expert, Long userId, RegisterScheduleDto registerScheduleDto) {
        Long scheduleId = scheduleCommandService
                .registerScheduleToUser(expert, userId, registerScheduleDto);

        String notificationComment = NotificationMessage.SCHEDULE_REGISTERED.format(expert.getName());

        userNotificationCommandService
                .sendNotificationToUser(userId, scheduleId, notificationComment, NotificationType.SCHEDULE);

        return scheduleId;
    }
}
