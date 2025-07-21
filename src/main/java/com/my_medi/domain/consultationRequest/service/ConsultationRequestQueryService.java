package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;

import java.util.List;

public interface ConsultationRequestQueryService {

    List<ConsultationRequest> getAllRequestByExpert(Long expertId);

    List<ConsultationRequest> getRequestByExpert(Long expertId, RequestStatus requestStatus);

    ConsultationRequest getRequestById(Long consultRequestId);


}