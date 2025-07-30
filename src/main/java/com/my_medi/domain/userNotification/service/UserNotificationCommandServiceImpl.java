package com.my_medi.domain.userNotification.service;

import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.userNotification.entity.NotificationMessage;
import com.my_medi.domain.userNotification.entity.NotificationType;
import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import com.my_medi.domain.userNotification.util.NotificationMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotificationCommandServiceImpl implements UserNotificationCommandService {
    private final UserRepository userRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public void sendConsultationRequestApproveNotificationToUser(Long userId, Long consultationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        String content = NotificationMessageFactory.create(
                NotificationMessage.MATCH_COMPLETE, "영양사 김민지"
        );

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationContent(content)
                .sourceId(consultationId)
                .notificationType(NotificationType.CONSULTATION_RESPONSE)
                .build();

        userNotificationRepository.save(userNotification);
    }

    @Override
    public void sendScheduleNotificationToUser(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        String content = NotificationMessageFactory.create(
                NotificationMessage.MATCH_COMPLETE, "영양사 김민지"
        );

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationContent(content)
                .sourceId(scheduleId)
                .notificationType(NotificationType.SCHEDULE)
                .build();

        userNotificationRepository.save(userNotification);
    }

    @Override
    public void removeNotification(Long notificationId) {
        userNotificationRepository.deleteById(notificationId);
    }
}
