package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserNotificationQueryService {
    Page<UserNotification> getUserNotificationListByPage(Long userId, Pageable pageable);
}
