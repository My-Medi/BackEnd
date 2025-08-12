package com.my_medi.domain.advice.service;

import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.repository.AdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdviceQueryServiceImpl implements AdviceQueryService {
    private final AdviceRepository adviceRepository;

    @Override
    public Page<Advice> getAdviceListByPageForUser(Long userId, Pageable pageable) {
        return adviceRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Advice> getAdviceListByPageForExpert(Long expertId, Long userId, Pageable pageable) {
        return adviceRepository.findAllByExpertIdAndUserId(expertId, userId, pageable);
    }

    @Override
    public Optional<Advice> getLatestAdviceOfUser(Long userId) {
        return adviceRepository.findTop1ByUserIdOrderByCreatedDateDescIdDesc(userId);
    }
}
