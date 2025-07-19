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

    /**
     * Retrieves all expert notifications associated with the specified expert ID.
     *
     * @param expertId the unique identifier of the expert
     * @return a list of ExpertNotification entities linked to the given expert ID
     */
    @Override
    public List<ExpertNotification> getNotificationByExpertId(Long expertId) {
        return expertNotificationRepository.findByExpertId(expertId);
    }

    /**
     * Retrieves an expert notification by its unique ID.
     *
     * @param notificationId the ID of the notification to retrieve
     * @return the corresponding ExpertNotification entity
     * @throws ExpertNotificationHandler.NOT_FOUND if no notification with the given ID exists
     */
    @Override
    public ExpertNotification getNotificationById(Long notificationId) {
        return expertNotificationRepository.findById(notificationId)
                .orElseThrow(() -> ExpertNotificationHandler.NOT_FOUND);
    }
}
