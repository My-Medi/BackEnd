package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;

public interface ConsultationRequestCommandService {

    Long requestConsultationToExpert(User user, Long expertId, String comment);

    Long editCommentOfRequest(Long consultationRequestId, Long userId, String comment);

    void cancelRequest(Long consultationRequestId, Long userId, RequestStatus status);

    void approveConsultation(Long consultationRequestId, Expert expert);

    void rejectConsultation(Long consultationRequestId, Expert expert);

    void removeApprovedConsultationByExpert(Long consultationRequestId, Expert expert);

}