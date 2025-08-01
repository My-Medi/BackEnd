package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.exception.ExpertNotificationHandler;
import com.my_medi.domain.notification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpertNotificationQueryServiceImpl implements ExpertNotificationQueryService {
    private final ExpertNotificationRepository expertNotificationRepository;

    @Override
    public List<ExpertNotification> getNotificationByExpertId(Long expertId) {
        return expertNotificationRepository.findByExpertId(expertId);
    }

    @Override
    public ExpertNotification getNotificationById(Long notificationId) {
        return expertNotificationRepository.findById(notificationId)
                .orElseThrow(() -> ExpertNotificationHandler.NOT_FOUND);
    }
}
