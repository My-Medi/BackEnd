package com.my_medi.domain.userNotification.service;

import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationCommandServiceImpl implements UserNotificationCommandService {
    private final UserNotificationRepository userNotificationRepository;

    public Long sendNotificationToUser(Long userId, Long sourceId) {

    }

    public void removeNotification(Long notificationId) {
        userNotificationRepository.deleteById(notificationId);
    }
}
