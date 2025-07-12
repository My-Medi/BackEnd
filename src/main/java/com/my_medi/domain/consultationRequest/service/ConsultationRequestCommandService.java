package com.my_medi.domain.consultationRequest.service;

public interface ConsultationRequestCommandService {

    Long requestConsultationToExpert(Long userId, Long expertId, String comment);

    Long editCommentOfRequest(Long consultationRequestId, String comment);

    void cancelRequest(Long consultationRequestId);
}
