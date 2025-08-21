package com.my_medi.api.schedule.service;

import com.my_medi.api.common.dto.NotificationEventDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.api.userNotification.mapper.UserNotificationConverter;
import com.my_medi.common.annotation.UseCase;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.schedule.service.ScheduleCommandService;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@UseCase
@RequiredArgsConstructor
public class ScheduleUseCase {
    private final ScheduleCommandService scheduleCommandService;
    private final UserNotificationCommandService userNotificationCommandService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Long registerScheduleAndSendNotificationToUser(
            Expert expert, Long userId, RegisterScheduleDto registerScheduleDto) {
        Long scheduleId = scheduleCommandService
                .registerScheduleToUser(expert, userId, registerScheduleDto);

        String notificationComment = NotificationMessage.SCHEDULE_REGISTERED.format(expert.getName());

        UserNotification userNotification = userNotificationCommandService
                .sendNotificationToUser(userId, scheduleId, notificationComment, NotificationType.SCHEDULE);

        //sse
        applicationEventPublisher.publishEvent(
                new NotificationEventDto.UserNotificationEventDto(
                        userNotification.getUser().getUsername(),
                        UserNotificationConverter.toUserNotification(userNotification)
                )
        );

        return scheduleId;
    }
}
