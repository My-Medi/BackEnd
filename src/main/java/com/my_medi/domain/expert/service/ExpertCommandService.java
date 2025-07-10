package com.my_medi.domain.expert.service;

import com.my_medi.api.member.dto.RegisterMemberDto;

public interface ExpertCommandService {

    //TODO [LATER] requestDto에 맞게 argument 변경해주기
    Long registerExpert(RegisterMemberDto registerMemberDto);


    //TODO [Now] 변경할 값들 argument로 받아오기 : dto 직접 생성
    Long updateExpertInformation(Long expertId);

    void deleteExpertAccount(Long expertId);
}
