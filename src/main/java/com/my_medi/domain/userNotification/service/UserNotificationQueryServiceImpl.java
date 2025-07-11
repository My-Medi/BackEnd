package com.my_medi.domain.userNotification.service;

import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserNotificationQueryServiceImpl implements UserNotificationQueryService {
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public List<UserNotification> getNotificationByUserId(Long userId) {
        return userNotificationRepository.findByUserId(userId);
    }

    @Override
    public UserNotification getNotificationById(Long notificationId) {
        return userNotificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다. id=" + notificationId));
    }
}
