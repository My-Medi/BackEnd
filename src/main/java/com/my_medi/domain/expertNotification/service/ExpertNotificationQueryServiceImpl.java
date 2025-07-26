package com.my_medi.domain.expertNotification.service;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import com.my_medi.domain.expertNotification.exception.ExpertNotificationHandler;
import com.my_medi.domain.expertNotification.repository.ExpertNotificationRepository;
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
        // findByExpertId 대신에 findWithExpertByExpertId 사용 -> n+1 문제
        return expertNotificationRepository.findWithExpertByExpertId(expertId);
    }

    @Override
    public ExpertNotification getNotificationById(Long notificationId) {
        return expertNotificationRepository.findById(notificationId)
                .orElseThrow(() -> ExpertNotificationHandler.NOT_FOUND);
    }
}
