package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;

import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsultationRequestQueryServiceImpl implements ConsultationRequestQueryService {

    private final ConsultationRequestRepository consultationRequestRepository;

    @Override
    public Page<ConsultationRequest> getAllRequestByExpert(Long expertId, Pageable pageable) {
        return consultationRequestRepository.findByExpertId(expertId, pageable);
    }

    @Override
    public Page<ConsultationRequest> getRequestByExpert(Long expertId, RequestStatus requestStatus, Pageable pageable) {
        return consultationRequestRepository.findByExpertIdAndRequestStatus(expertId, requestStatus, pageable);
    }

    @Override
    public ConsultationRequest getRequestById(Long consultRequestId) {
        return consultationRequestRepository.findById(consultRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);
    }

    @Override
    public List<ConsultationRequest> getAllRequestByUser(Long userId) {
        return consultationRequestRepository.findByUserId(userId);
    }

    @Override
    public List<ConsultationRequest> getRequestByUser(Long userId, RequestStatus requestStatus) {
        return consultationRequestRepository.findByUserIdAndRequestStatus(userId, requestStatus);
    }

    @Override
    public ConsultationRequest getRequestedExpertDetail(Long expertId) {
        List<ConsultationRequest> results =
                consultationRequestRepository.findLatestRequestedByExpert(expertId, RequestStatus.REQUESTED);

        return results.stream()
                .findFirst()
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);
    }

}

