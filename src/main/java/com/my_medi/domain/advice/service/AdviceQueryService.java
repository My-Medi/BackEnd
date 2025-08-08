package com.my_medi.domain.advice.service;

import com.my_medi.domain.advice.entity.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdviceQueryService {
    Page<Advice> getAdviceListByPage(Long userId, Pageable pageable);
    Optional<Advice> getLatestAdviceOfUser(Long userId);
}
