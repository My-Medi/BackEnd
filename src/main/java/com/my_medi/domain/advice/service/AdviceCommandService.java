package com.my_medi.domain.advice.service;

import com.my_medi.api.advice.dto.AdviceRequestDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;

public interface AdviceCommandService {
    Long registExpertAdviceToUser(Expert expert, User use, AdviceRequestDto adviceRequestDto);

    Long editExpertAdviceToUser(Long adviceId, AdviceRequestDto adviceRequestDto);

    Long deleteExpertAdviceFromUser(Long adviceId);
}
