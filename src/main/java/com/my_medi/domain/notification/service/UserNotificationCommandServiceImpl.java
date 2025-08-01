package com.my_medi.domain.notification.service;

import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
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
    public void sendNotificationToUser(Long userId, Long sourceId, String comment, NotificationType notificationType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationContent(comment)
                .sourceId(sourceId)
                .notificationType(notificationType)
                .isRead(false)
                .build();

        userNotificationRepository.save(userNotification);
    }

    @Override
    public void removeNotification(Long notificationId) {
        userNotificationRepository.deleteById(notificationId);
    }
}
