package com.my_medi.api.consultation.service;

import com.my_medi.domain.expertNotification.service.ExpertNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationToExpertUseCase {
    private final ExpertNotificationCommandService expertNotificationCommandService;

    public void SendConsultationRequestNotificationToExpert(Long expertId, Long requestId) {
        expertNotificationCommandService.sendNotificationToExpert(expertId, requestId);
    }
}
