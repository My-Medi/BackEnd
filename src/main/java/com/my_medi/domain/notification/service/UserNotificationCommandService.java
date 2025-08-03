package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.NotificationType;

public interface UserNotificationCommandService {
    void sendNotificationToUser(Long userId, Long sourceId, String comment, NotificationType notificationType);

    Long readNotification(Long userId, Long sourceId);

    void removeNotification(Long notificationId);
}
