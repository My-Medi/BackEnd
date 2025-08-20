package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.entity.UserNotification;

import java.util.List;

public interface UserNotificationCommandService {
     UserNotification sendNotificationToUser(Long userId, Long sourceId, String comment, NotificationType notificationType);

    Long readUserNotification(Long notificationId);

    void removeNotifications(List<Long> notificationId);

    void sendDummyNotificationToUser(Long userId, int count);
}
