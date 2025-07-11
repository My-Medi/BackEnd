package com.my_medi.domain.expertNotification.service;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import com.my_medi.domain.expertNotification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertNotificationQueryServiceImpl implements ExpertNotificationQueryService {
    private final ExpertNotificationRepository expertNotificationRepository;

    public List<ExpertNotification> getNotificationByExpertId(Long expertId) {
        return expertNotificationRepository.findByExpertId(expertId);
    }

    public ExpertNotification getNotificationById(Long notificationId) {
        return expertNotificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다. id=" + notificationId));
    }
}
