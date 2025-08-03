package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpertNotificationQueryService {
    Page<ExpertNotification> getExpertNotificaitonListByPage(Long expertId, Pageable pageable);
}
