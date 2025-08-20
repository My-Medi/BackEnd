package com.my_medi.domain.notification.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.exception.UserNotificationHandler;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotificationCommandServiceImpl implements UserNotificationCommandService {
    private final UserRepository userRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public UserNotification sendNotificationToUser(Long userId, Long sourceId, String comment, NotificationType notificationType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationContent(comment)
                .sourceId(sourceId)
                .notificationType(notificationType)
                .isRead(false)
                .build();

        return userNotificationRepository.save(userNotification);
    }

    @Override
    public Long readUserNotification(Long notificationId) {
        UserNotification userNotification = userNotificationRepository
                .findById(notificationId).orElseThrow(() -> UserNotificationHandler.NOT_FOUND);

        userNotification.updateIsReadState();

        return userNotification.getId();
    }

    @Override
    public void removeNotifications(List<Long> notificationId) {
        userNotificationRepository.deleteAllById(notificationId);
    }

    @Override
    public void sendDummyNotificationToUser(Long userId, int count) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        List<UserNotification> userNotificationList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            userNotificationList.add(
                    UserNotification.builder()
                            .user(user)
                            .notificationContent(i % 3 == 0 ?
                                    NotificationMessage.CONSULTATION_APPROVED.format("아무개") :
                                    NotificationMessage.SCHEDULE_REGISTERED.format("아무개"))
                            .notificationType(i % 3 == 0 ?
                                    NotificationType.CONSULTATION_RESPONSE :
                                    NotificationType.SCHEDULE)
                            .sourceId(1L)       //존재하는지 존재하지 않는지 알 수 없음
                            .isRead(i % 2 == 0 ? true : false)
                            .build()
            );
        }
        userNotificationRepository.saveAll(userNotificationList);
    }
}
