package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.NotificationType;

import java.util.List;

public interface UserNotificationCommandService {
    void sendNotificationToUser(Long userId, Long sourceId, String comment, NotificationType notificationType);

    Long readUserNotification(Long notificationId);

    void removeNotifications(List<Long> notificationId);
}
