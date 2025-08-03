package com.my_medi.domain.notification.service;

public interface ExpertNotificationCommandService {
    void sendNotificationToExpert(Long expertId, Long sourceId, String comment);

    Long readExpertNotification(Long notificationId);

    void removeNotification(Long notificationId);
}
