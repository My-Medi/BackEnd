package com.my_medi.api.consultation.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expertNotification.service.ExpertNotificationCommandService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.userNotification.service.UserNotificationCommandService;
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

        userNotificationCommandService.
                sendConsultationRequestApproveNotificationToUser(request.getUser().getId(), consultationId);
    }

    public Long sendConsultationRequestNotificationToExpert(User user, Long expertId, String comment) {
        Long requestId = consultationRequestCommandService.requestConsultationToExpert(user, expertId, comment);

        expertNotificationCommandService.sendNotificationToExpert(expertId, requestId);

        return requestId;
    }
}
