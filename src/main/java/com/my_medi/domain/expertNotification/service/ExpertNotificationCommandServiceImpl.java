package com.my_medi.domain.expertNotification.service;

import com.my_medi.domain.expertNotification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertNotificationCommandServiceImpl implements ExpertNotificationCommandService {
    private final ExpertNotificationRepository expertNotificationRepository;

    public Long sendNotificationToExpert(Long expertId, Long sourceId) {

    }

    public void removeNotification(Long notificationId) {
        expertNotificationRepository.deleteById(notificationId);
    }
}
