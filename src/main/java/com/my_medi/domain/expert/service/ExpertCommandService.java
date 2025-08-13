package com.my_medi.domain.expert.service;

import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.expert.dto.UpdateProfileDto;
import com.my_medi.api.expert.dto.UpdateResumeDto;

public interface ExpertCommandService {

    Long registerExpert(RegisterExpertDto registerExpertDto);

    // 피그마 [회원정보 수정페이지-전문가] 페이지
    Long updateProfile(Long expertId, UpdateProfileDto dto);

    // 피그마 [이력서 관리] 페이지
    Long updateResume(Long expertId, UpdateResumeDto dto);

    void deleteExpertAccount(Long expertId);

    void registerDummyExperts(int count);
}
