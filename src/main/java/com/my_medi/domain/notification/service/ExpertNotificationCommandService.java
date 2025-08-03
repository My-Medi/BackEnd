package com.my_medi.domain.notification.service;

public interface ExpertNotificationCommandService {
    void sendNotificationToExpert(Long expertId, Long sourceId, String comment);

    Long readNotification(Long expertId, Long sourceId);

    void removeNotification(Long notificationId);
}
