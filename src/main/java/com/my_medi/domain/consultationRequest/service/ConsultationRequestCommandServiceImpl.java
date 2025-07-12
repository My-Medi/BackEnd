package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
<<<<<<< HEAD
=======
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestErrorStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
>>>>>>> develop
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
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        request.updateComment(comment);
        return request.getId();
    }

    //TODO : 유저 체크하기
    @Override
    public void cancelRequest(Long consultationRequestId) {
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() ->ConsultationRequestHandler.NOT_FOUND);

        consultationRequestRepository.delete(request);
    }
}
