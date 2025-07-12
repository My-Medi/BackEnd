package com.my_medi.domain.userNotification.service;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.exception.UserNotificationHandler;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
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
        return userNotificationRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotificationHandler(ErrorStatus.USER_NOTIFICATION_BY_USER_ID_NOT_FOUND));
    }

    @Override
    public UserNotification getNotificationById(Long notificationId) {
        return userNotificationRepository.findById(notificationId)
                .orElseThrow(() -> new UserNotificationHandler(ErrorStatus.USER_NOTIFICATION_BY_ID_NOT_FOUND));
    }
}
