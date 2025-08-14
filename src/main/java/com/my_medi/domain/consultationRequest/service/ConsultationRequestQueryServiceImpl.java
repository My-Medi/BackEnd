package com.my_medi.domain.consultationRequest.service;

import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.api.consultation.mapper.UserConsultationConvert;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;

import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsultationRequestQueryServiceImpl implements ConsultationRequestQueryService {

    private final ConsultationRequestRepository consultationRequestRepository;
    private final ExpertRepository expertRepository;


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
    @Transactional(readOnly = true)
    public UserConsultationDto.ExpertRequestedDto getRequestedExpertDetail(Long userId, Long expertId) {
        var agg = consultationRequestRepository.findRequestedAgg(userId, expertId, RequestStatus.REQUESTED);

        if (agg.getCount() == 0L || agg.getLatestCreated() == null) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        LocalDate requestedAt = agg.getLatestCreated().toLocalDate();

        return UserConsultationConvert.toRequestedDetailDto(
                expert,
                Math.toIntExact(agg.getCount()),
                requestedAt
        );
    }


    @Override
    public ConsultationRequest getMatchedExpertDetail(Long userId, Long expertId) {
        return consultationRequestRepository
                .findMatchedRequest(userId, expertId, RequestStatus.ACCEPTED)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);
    }
}

