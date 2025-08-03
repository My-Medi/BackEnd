package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;

import com.my_medi.domain.consultationRequest.exception.ConsultationRequestErrorStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationRequestCommandServiceImpl implements ConsultationRequestCommandService {

    private final ConsultationRequestRepository consultationRequestRepository;
    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;

    @Override
    public Long requestConsultationToExpert(User user, Long expertId, String comment) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_NOT_FOUND));

        long existingCount = consultationRequestRepository.countByUserIdAndExpertId(user.getId(), expertId);
        if (existingCount >= 5) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.CONSULTATION_LIMIT_EXCEEDED);
        }

        boolean hasInvalidStatus = consultationRequestRepository.existsByUserIdAndExpertIdAndRequestStatusIn(
                user.getId(),
                expertId,
                List.of(RequestStatus.REJECTED, RequestStatus.ACCEPTED)
        );
        if (hasInvalidStatus) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.ALREADY_PROCESSED_CONSULTATION);
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
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER);
        }

        request.updateComment(comment);
        return request.getId();
    }

    @Override
    public void cancelRequest(Long consultationRequestId, Long userId) {
        ConsultationRequest request = getRequestedConsultation(consultationRequestId);

        if (!request.getUser().getId().equals(userId)) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.REQUEST_ONLY_CAN_BE_TOUCHED_BY_USER);
        }

        consultationRequestRepository.delete(request);
    }

    @Override
    public void approveConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);
        validateExpertApprovalOrRejectionAuthority(request, expert);
        request.approve();

        Long userId = request.getUser().getId();
        Long expertId = expert.getId();

        List<ConsultationRequest> toDelete = consultationRequestRepository
                .findByUserIdAndExpertIdAndStatus(userId, expertId, RequestStatus.REQUESTED)
                .stream()
                .filter(req -> !req.getId().equals(consultationId))
                .toList();

        consultationRequestRepository.deleteAllInBatch(toDelete);
    }

    @Override
    public void rejectConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);
        validateExpertApprovalOrRejectionAuthority(request, expert);
        request.reject();
    }

    private void validateExpertApprovalOrRejectionAuthority(ConsultationRequest request, Expert expert) {
        if (!request.getExpert().getId().equals(expert.getId())) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_MISMATCH);
        }

        boolean hasConflict = consultationRequestRepository.existsByExpertIdAndUserIdAndRequestStatusNot(
                expert.getId(),
                request.getUser().getId(),
                RequestStatus.REQUESTED
        );

        if (hasConflict) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.DUPLICATED_CONSULTATION);
        }
    }


    private ConsultationRequest getRequestedConsultation(Long id) {
        ConsultationRequest request = consultationRequestRepository.findById(id)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (request.getRequestStatus() != RequestStatus.REQUESTED) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.INVALID_REQUEST_STATUS);
        }

        return request;
    }

    @Override
    public void removeApprovedConsultationByExpert(Long consultationRequestId, Expert expert) {
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (!request.getExpert().getId().equals(expert.getId())) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_MISMATCH);
        }

        if (request.getRequestStatus() != RequestStatus.ACCEPTED) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.INVALID_REQUEST_STATUS);
        }

        consultationRequestRepository.delete(request);
    }
}
