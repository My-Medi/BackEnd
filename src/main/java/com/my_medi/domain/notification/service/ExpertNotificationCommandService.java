package com.my_medi.domain.notification.service;

public interface ExpertNotificationCommandService {
    void sendNotificationToExpert(Long expertId, Long sourceId, String comment);

    void removeNotification(Long notificationId);
}
