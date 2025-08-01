package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsultationRequestQueryService {

    ConsultationRequest getRequestById(Long consultRequestId);

    List<ConsultationRequest> getAllRequestByUser(Long userId);

    List<ConsultationRequest> getRequestByUser(Long userId, RequestStatus requestStatus);

    Page<ConsultationRequest> getRequestByExpert(Long expertId, RequestStatus status, Pageable pageable);

    Page<ConsultationRequest> getAllRequestByExpert(Long expertId, Pageable pageable);
}