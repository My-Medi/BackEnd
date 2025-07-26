package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.expert.entity.Expert;

public interface ConsultationRequestCommandService {

    //TODO make logic of removeApprovedConsultationByExpert
    Long requestConsultationToExpert(Long userId, Long expertId, String comment);

    Long editCommentOfRequest(Long consultationRequestId, String comment);

    void cancelRequest(Long consultationRequestId);

    void approveConsultation(Long consultationRequestId, Expert expert);

    void rejectConsultation(Long consultationRequestId, Expert expert);
}