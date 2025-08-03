package com.my_medi.domain.notification.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.exception.ExpertNotificationHandler;
import com.my_medi.domain.notification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpertNotificationCommandServiceImpl implements ExpertNotificationCommandService {
    private final ExpertRepository expertRepository;
    private final ExpertNotificationRepository expertNotificationRepository;

    @Override
    public void sendNotificationToExpert(Long expertId, Long sourceId, String comment) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        ExpertNotification expertNotification = ExpertNotification.builder()
                .expert(expert)
                .notificationContent(comment)
                .sourceId(sourceId)
                .isRead(false)
                .build();

        expertNotificationRepository.save(expertNotification);
    }

    @Override
    public Long readExpertNotification(Long notificationId) {
        ExpertNotification expertNotification = expertNotificationRepository.findById(notificationId)
                .orElseThrow(() -> ExpertNotificationHandler.NOT_FOUND);

        expertNotification.updateIsReadState();

        return expertNotification.getId();
    }

    @Override
    public void removeNotifications(List<Long> notificationId) {
        expertNotificationRepository.deleteAllById(notificationId);
    }
}
