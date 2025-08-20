package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;

import com.my_medi.domain.consultationRequest.exception.ConsultationRequestErrorStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO exception 처리 통일 (not create object newly)
@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationRequestCommandServiceImpl implements ConsultationRequestCommandService {

    private final ConsultationRequestRepository consultationRequestRepository;
    private final ExpertRepository expertRepository;

    @Override
    public Long requestConsultationToExpert(User user, Long expertId, String comment) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_NOT_FOUND));

        long existingCount = consultationRequestRepository.countByUserIdAndExpertId(user.getId(), expertId);
        if (existingCount >= 5) {
            throw ConsultationRequestHandler.CONSULTATION_LIMIT_EXCEEDED;
        }

        boolean hasInvalidStatus = consultationRequestRepository.existsByUserIdAndExpertIdAndRequestStatusIn(
                user.getId(),
                expertId,
                List.of(RequestStatus.REJECTED, RequestStatus.ACCEPTED)
        );
        if (hasInvalidStatus) {
            throw ConsultationRequestHandler.ALREADY_PROCESSED_CONSULTATION;
        }

        ConsultationRequest request = ConsultationRequest.builder()
                .user(user)
                .expert(expert)
                .comment(comment)
                .requestStatus(RequestStatus.REQUESTED)
                .build();

        return consultationRequestRepository.save(request).getId();
    }


    @Override
    public Long editCommentOfRequest(Long consultationRequestId, Long userId, String comment) {

        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (!request.getUser().getId().equals(userId)) {
            throw ConsultationRequestHandler.REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER;
        }

        request.updateComment(comment);
        return request.getId();
    }

    @Override
    @Transactional
    public void cancelRequest(Long consultationRequestId,
                              Long userId,
                              RequestStatus status) {

        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (request.getRequestStatus() == RequestStatus.REJECTED) {
            throw ConsultationRequestHandler.INVALID_STATUS;
        }

        if(!request.getUser().getId().equals(userId)) {
            throw ConsultationRequestHandler.FORBIDDEN_REQUEST_OWNER_MISMATCH;
        }

        if(!request.getRequestStatus().equals(status)) {
            throw ConsultationRequestHandler.REQUEST_STATUS_MISMATCH;
        }


    }



    @Override
    public void approveConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);
        validateExpertApprovalOrRejectionAuthority(request, expert);
        request.approve();
        deleteOtherRequestedConsultations(request);

    }

    @Override
    public void rejectConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);
        validateExpertApprovalOrRejectionAuthority(request, expert);
        request.reject();
        deleteOtherRequestedConsultations(request);
    }

    private void validateExpertApprovalOrRejectionAuthority(ConsultationRequest request, Expert expert) {
        if (!request.getExpert().getId().equals(expert.getId())) {
            throw ConsultationRequestHandler.EXPERT_MISMATCH;
        }

        boolean hasConflict = consultationRequestRepository.existsByExpertIdAndUserIdAndRequestStatusNot(
                expert.getId(),
                request.getUser().getId(),
                RequestStatus.REQUESTED
        );

        if (hasConflict) {
            throw ConsultationRequestHandler.DUPLICATED_CONSULTATION;
        }
    }

    private void deleteOtherRequestedConsultations(ConsultationRequest request) {
        List<ConsultationRequest> toDelete = consultationRequestRepository
                .findByUserIdAndExpertIdAndRequestStatus(
                        request.getUser().getId(),
                        request.getExpert().getId(),
                        RequestStatus.REQUESTED
                ).stream()
                .filter(req -> !req.getId().equals(request.getId()))
                .toList();

        consultationRequestRepository.deleteAllInBatch(toDelete);
    }


    private ConsultationRequest getRequestedConsultation(Long id) {
        ConsultationRequest request = consultationRequestRepository.findById(id)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (request.getRequestStatus() != RequestStatus.REQUESTED) {
            throw ConsultationRequestHandler.INVALID_REQUEST_STATUS;
        }

        return request;
    }

    @Override
    public void removeApprovedConsultationByExpert(Long consultationRequestId, Expert expert) {
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (!request.getExpert().getId().equals(expert.getId())) {
            throw ConsultationRequestHandler.EXPERT_MISMATCH;
        }

        if (request.getRequestStatus() != RequestStatus.ACCEPTED) {
            throw ConsultationRequestHandler.INVALID_REQUEST_STATUS;
        }

        consultationRequestRepository.delete(request);
    }
}
