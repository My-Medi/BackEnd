package com.my_medi.api.consultation.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.userNotification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationToUserUseCase {
    private final ConsultationRequestQueryService consultationQueryCommandService;
    private final UserNotificationCommandService userNotificationCommandService;

    public void sendConsultationRequestApproveNotificationToUser(Long consultationId) {
        ConsultationRequest request = consultationQueryCommandService.getRequestById(consultationId);

        userNotificationCommandService.sendNotificationToUser(request.getUser().getId(), consultationId);
    }
}
