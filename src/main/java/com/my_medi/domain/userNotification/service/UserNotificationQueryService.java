package com.my_medi.domain.userNotification.service;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import com.my_medi.domain.userNotification.entity.UserNotification;

import java.util.List;

public interface UserNotificationQueryService {
    List<UserNotification> getNotificationByUserId(Long userId);

    UserNotification getNotificationById(Long notificationId);
}
