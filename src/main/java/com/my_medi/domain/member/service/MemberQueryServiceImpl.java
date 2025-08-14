package com.my_medi.domain.member.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //queryservice에는 readonly
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;

    //TODO member handler 새로 생성하기
    @Override
    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
    }
    @Override
    public Member getByKakaoEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
    }

    @Override
    public Member getByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(()->ExpertHandler.NOT_FOUND);
    }

    @Override
    public boolean validateExistNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public boolean validateExistLoginId(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }
}

