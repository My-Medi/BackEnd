package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsultationRequestQueryServiceImpl implements ConsultationRequestQueryService {

    private final ConsultationRequestRepository consultationRequestRepository;

    @Override
    public List<ConsultationRequest> getAllRequestByExpert(Long expertId) {
        return consultationRequestRepository.findByExpertId(expertId);
    }

    @Override
    public List<ConsultationRequest> getRequestByExpert(Long expertId, RequestStatus requestStatus) {
        return consultationRequestRepository.findByExpertIdAndRequestStatus(expertId, requestStatus);
    }

    @Override
    public ConsultationRequest getRequestById(Long consultRequestId) {
        return consultationRequestRepository.findById(consultRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);
    }
}
