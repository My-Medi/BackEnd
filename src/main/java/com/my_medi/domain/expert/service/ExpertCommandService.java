package com.my_medi.domain.expert.service;

import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.expert.dto.UpdateExpertDto;

public interface ExpertCommandService {

    Long registerExpert(RegisterExpertDto registerExpertDto);

    Long updateExpertInformation(Long expertId, UpdateExpertDto dto);

    void deleteExpertAccount(Long expertId);

    void registerDummyExperts(int count);
}
