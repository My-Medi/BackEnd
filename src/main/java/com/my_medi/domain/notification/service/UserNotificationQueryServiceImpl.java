package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNotificationQueryServiceImpl implements UserNotificationQueryService {
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public Page<UserNotification> getUserNotificationListByPage(Long userId, Pageable pageable) {
        return userNotificationRepository.findAllByUserId(userId, pageable);
    }
}
