package com.my_medi.api.advice.service;

import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.service.AdviceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceUseCase {
    private final AdviceQueryService adviceQueryService;

    public Page<Advice> getPrioritizedAdviceDtoPageByUserId(Long userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.desc("createdDate"), Sort.Order.desc("id"))
        );

        return adviceQueryService.getAdviceListByPageForUser(userId, pageable);
    }

    public Page<Advice> getPrioritizedAdviceDtoPageByExpertId(Long expertId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.desc("createdDate"), Sort.Order.desc("id"))
        );

        return adviceQueryService.getAdviceListByPageForExpert(expertId, pageable);
    }
}
