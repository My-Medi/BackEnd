package com.my_medi.domain.advice.service;

import com.my_medi.api.advice.dto.AdviceRequestDto;
import com.my_medi.domain.expert.entity.Expert;

public interface AdviceCommandService {
    Long registerExpertAdviceToUser(Expert expert, Long userId, AdviceRequestDto adviceRequestDto);

    Long editExpertAdviceToUser(Long adviceId, AdviceRequestDto adviceRequestDto);

    Void deleteExpertAdviceFromUser(Long adviceId);
}
