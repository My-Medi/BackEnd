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

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationRequestCommandServiceImpl implements ConsultationRequestCommandService {

    private final ConsultationRequestRepository consultationRequestRepository;
    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;

    @Override
    public Long requestConsultationToExpert(Long userId, Long expertId, String comment) {
        //TODO[1] user와 expert 사이의 consultation 엔티티는 총 5개가 maximum
        //TODO[2] 이미 reject가 됐거나 approved가 된 consultation이 있다면 throw
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ConsultationRequestHandler(ConsultationRequestErrorStatus.USER_NOT_FOUND));
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ConsultationRequestHandler(ConsultationRequestErrorStatus.EXPERT_NOT_FOUND));

        ConsultationRequest request = ConsultationRequest.builder()
                .user(user)
                .expert(expert)
                .comment(comment)
                .requestStatus(RequestStatus.REQUESTED)
                .build();

        return consultationRequestRepository.save(request).getId();
    }

    @Override
    public Long editCommentOfRequest(Long consultationRequestId, String comment) {
        //TODO 유저 체크하기
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        request.updateComment(comment);
        return request.getId();
    }

    //TODO : 유저 체크하기
    @Override
    public void cancelRequest(Long consultationRequestId) {
        ConsultationRequest request = getRequestedConsultation(consultationRequestId);
        consultationRequestRepository.delete(request);
    }

    @Override
    public void approveConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);

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

        request.approve();
    }

    @Override
    public void rejectConsultation(Long consultationId, Expert expert) {
        ConsultationRequest request = getRequestedConsultation(consultationId);

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

        request.reject();
    }

    private ConsultationRequest getRequestedConsultation(Long id) {
        ConsultationRequest request = consultationRequestRepository.findById(id)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        if (request.getRequestStatus() != RequestStatus.REQUESTED) {
            throw new ConsultationRequestHandler(ConsultationRequestErrorStatus.INVALID_REQUEST_STATUS);
        }

        return request;
    }
}
