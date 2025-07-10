package com.my_medi.domain.expertNotification.service;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;

import java.util.List;

public interface ExpertNotificationQueryService {

    List<ExpertNotification> getNotificationByExpertId(Long expertId);

    ExpertNotification getNotificationById(Long notificationId);
}
