package com.my_medi.domain.notification.service;

import java.util.List;

public interface ExpertNotificationCommandService {
    void sendNotificationToExpert(Long expertId, Long sourceId, String comment);

    Long readExpertNotification(Long notificationId);

    void removeNotifications(List<Long> notificationId);
}
