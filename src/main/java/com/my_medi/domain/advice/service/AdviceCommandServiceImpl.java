package com.my_medi.domain.advice.service;

import com.my_medi.api.advice.dto.AdviceRequestDto;
import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.exception.AdviceHandler;
import com.my_medi.domain.advice.repository.AdviceRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdviceCommandServiceImpl implements AdviceCommandService {
    private final UserRepository userRepository;
    private final AdviceRepository adviceRepository;

    @Override
    public Long registerExpertAdviceToUser(Expert expert, Long userId, AdviceRequestDto adviceRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        Advice advice = Advice.builder()
                .user(user)
                .expert(expert)
                .adviceComment(adviceRequestDto.getAdviceComment())
                .build();

        adviceRepository.save(advice);

        return advice.getId();
    }

    @Override
    public Long editExpertAdviceToUser(Long adviceId, AdviceRequestDto adviceRequestDto) {
        Advice advice = adviceRepository.findById(adviceId)
                .orElseThrow(() -> AdviceHandler.NOT_FOUND);

        advice.updateAdvice(adviceRequestDto.getAdviceComment());

        return advice.getId();
    }

    @Override
    public Void deleteExpertAdviceFromUser(Long adviceId) {
        adviceRepository.deleteById(adviceId);
        return null;
    }
}
