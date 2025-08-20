package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;

import java.util.List;

public interface ExpertNotificationCommandService {
    ExpertNotification sendNotificationToExpert(Long expertId, Long sourceId, String comment);

    Long readExpertNotification(Long notificationId);

    void removeNotifications(List<Long> notificationId);

    void sendDummyNotificationToExpert(Long expertId, int count);
}
