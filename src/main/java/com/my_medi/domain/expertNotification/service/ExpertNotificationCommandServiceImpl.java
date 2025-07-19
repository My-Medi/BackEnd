package com.my_medi.domain.expertNotification.service;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import com.my_medi.domain.expertNotification.exception.ExpertNotificationHandler;
import com.my_medi.domain.expertNotification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpertNotificationCommandServiceImpl implements ExpertNotificationCommandService {
    private final ExpertRepository expertRepository;
    private final ConsultationRequestRepository consultationRequestRepository;
    private final ExpertNotificationRepository expertNotificationRepository;

    /**
     * Creates and saves a notification for the specified expert based on a consultation request.
     *
     * Retrieves the expert and consultation request by their IDs, constructs a new expert notification using the consultation request's comment as the notification content, and saves it. Returns the ID of the created notification.
     *
     * @param expertId the ID of the expert to notify
     * @param sourceId the ID of the consultation request serving as the notification source
     * @return the ID of the newly created expert notification
     * @throws ExpertHandler.NOT_FOUND if the expert does not exist
     * @throws ConsultationRequestHandler.NOT_FOUND if the consultation request does not exist
     */
    @Override
    public Long sendNotificationToExpert(Long expertId, Long sourceId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        ConsultationRequest consultationRequest = consultationRequestRepository.findById(sourceId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        ExpertNotification expertNotification = ExpertNotification.builder()
                .expert(expert)
                .notificationContent(consultationRequest.getComment())
                .sourceId(sourceId)
                .build();

        expertNotificationRepository.save(expertNotification);

        return expertNotification.getId();
    }

    @Override
    public void removeNotification(Long notificationId) {
        expertNotificationRepository.deleteById(notificationId);
    }
}
