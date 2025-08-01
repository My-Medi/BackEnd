package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import java.util.List;

public interface UserNotificationQueryService {
    List<UserNotification> getNotificationByUserId(Long userId);

    UserNotification getNotificationById(Long notificationId);
}
