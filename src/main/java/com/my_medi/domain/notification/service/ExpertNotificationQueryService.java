package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;
import java.util.List;

public interface ExpertNotificationQueryService {

    List<ExpertNotification> getNotificationByExpertId(Long expertId);

    ExpertNotification getNotificationById(Long notificationId);
}
