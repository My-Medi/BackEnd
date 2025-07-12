package com.my_medi.domain.consultationRequest.service;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationRequestCommandServiceImpl implements ConsultationRequestCommandService {

    private final ConsultationRequestRepository consultationRequestRepository;
    private final MemberRepository memberRepository;
    private final ExpertRepository expertRepository;

    @Override
    public Long requestConsultationToExpert(Long userId, Long expertId, String comment) {
        Member user = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new IllegalArgumentException("Expert not found"));

        ConsultationRequest request = ConsultationRequest.builder()
                .user(user)
                .expert(expert)
                .comment(comment)
                .status(RequestStatus.PENDING)
                .build();

        return consultationRequestRepository.save(request).getId();
    }

    @Override
    public Long editCommentOfRequest(Long consultationRequestId, String comment) {
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Consultation request not found"));

        request.updateComment(comment);
        return request.getId();
    }

    @Override
    public void cancelRequest(Long consultationRequestId) {
        ConsultationRequest request = consultationRequestRepository.findById(consultationRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Consultation request not found"));

        request.cancel(); // 내부에서 status를 CANCELED로 바꾸는 메서드
    }
}
