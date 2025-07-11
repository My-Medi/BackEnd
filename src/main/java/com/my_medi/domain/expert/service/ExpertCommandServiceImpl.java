package com.my_medi.domain.expert.service;

import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ExpertCommandServiceImpl implements ExpertCommandService {
    private final ExpertRepository expertRepository;

    @Override
    public Long registerExpert(RegisterMemberDto registerMemberDto) {
        //TODO [LATER] requestDto에 맞게 argument 변경해주기
        return null;
    }

    @Override
    public Long updateExpertInformation(Long expertId, UpdateExpertDto dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expert.modifyInfo(dto);
        return expert.getId();
    }

    @Override
    public void deleteExpertAccount(Long expertId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expertRepository.delete(expert); // hard delete
    }



}