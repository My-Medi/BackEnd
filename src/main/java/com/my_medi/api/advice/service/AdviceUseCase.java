package com.my_medi.api.advice.service;

import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.common.annotation.UseCase;
import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.exception.AdviceHandler;
import com.my_medi.domain.advice.repository.AdviceRepository;
import com.my_medi.domain.advice.service.AdviceQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.my_medi.common.consts.StaticVariable.ADVICE_ID;
import static com.my_medi.common.consts.StaticVariable.CREATED_DATE;

@UseCase
@RequiredArgsConstructor
public class AdviceUseCase {
    private final AdviceQueryService adviceQueryService;
    private final AdviceRepository adviceRepository;
    private final UserRepository userRepository;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;

    public Page<Advice> getPrioritizedAdviceDtoPageByUserId(Long userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.desc(CREATED_DATE), Sort.Order.desc(ADVICE_ID))
        );

        return adviceQueryService.getAdviceListByPageForUser(userId, pageable);
    }

    public Page<Advice> getPrioritizedAdviceDtoPageByExpertId(Long expertId,
                                                              Long userId,
                                                              Integer currentPage,
                                                              Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.desc(CREATED_DATE), Sort.Order.desc(ADVICE_ID))
        );

        return adviceQueryService.getAdviceListByPageForExpert(expertId, userId, pageable);
    }

    public void validateAdvice(Long expertId, Long adviceId) {
        Advice advice = adviceRepository.findById(adviceId)
                .orElseThrow(() -> AdviceHandler.NOT_FOUND);

        if (!advice.getExpert().getId().equals(expertId)) {
            throw AdviceHandler.FORBIDDEN;
        }
    }
}
