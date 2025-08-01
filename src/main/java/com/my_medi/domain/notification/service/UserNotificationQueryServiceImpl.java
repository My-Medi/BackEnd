package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.exception.UserNotificationHandler;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNotificationQueryServiceImpl implements UserNotificationQueryService {
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public List<UserNotification> getNotificationByUserId(Long userId) {
        return userNotificationRepository.findByUserId(userId);
    }

    @Override
    public UserNotification getNotificationById(Long notificationId) {
        return userNotificationRepository.findById(notificationId)
                .orElseThrow(() -> UserNotificationHandler.NOT_FOUND);
    }
}
