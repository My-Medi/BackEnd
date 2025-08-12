package com.my_medi.api.consultation.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationUseCase {
    private final ConsultationRequestCommandService consultationRequestCommandService;
    private final ConsultationRequestQueryService consultationRequestQueryService;
    private final UserNotificationCommandService userNotificationCommandService;
    private final ExpertNotificationCommandService expertNotificationCommandService;

    public void approveConsultationRequestAndSendNotificationToUser(Expert expert, Long consultationId) {
        consultationRequestCommandService.approveConsultation(consultationId, expert);
        ConsultationRequest request = consultationRequestQueryService.getRequestById(consultationId);

        String notificationComment = NotificationMessage
                .CONSULTATION_APPROVED.format(expert.getName());

        userNotificationCommandService.sendNotificationToUser(request.getUser().getId(),
                consultationId, notificationComment, NotificationType.CONSULTATION_RESPONSE);
    }

    public void rejectConsultationRequestAndSendNotificationToUser(Expert expert, Long consultationId) {
        consultationRequestCommandService.rejectConsultation(consultationId, expert);
        ConsultationRequest request = consultationRequestQueryService.getRequestById(consultationId);

        String notificationComment = NotificationMessage
                .CONSULTATION_REJECTED.format(expert.getName());

        userNotificationCommandService.sendNotificationToUser(request.getUser().getId(),
                consultationId, notificationComment, NotificationType.CONSULTATION_RESPONSE);
    }

    //TODO return type 통일하기 Long -> void
    public Long sendConsultationRequestNotificationToExpert(User user, Long expertId, String comment) {
        Long requestId = consultationRequestCommandService.requestConsultationToExpert(user, expertId, comment);

        String notificationComment = NotificationMessage.CONSULTATION_REQUESTED.format(user.getName());

        expertNotificationCommandService.sendNotificationToExpert(expertId, requestId, notificationComment);

        return requestId;
    }
}
