package com.my_medi.domain.expert.service;

import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertErrorStatus;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //queryservice에는 readonly
public class ExpertQueryServiceImpl implements ExpertQueryService {
    private final ExpertRepository expertRepository;

    @Override
    public Expert getExpertById(Long expertId) {
        return expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
    }

    @Override
    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    @Override
    public Expert getExpertByUsername(String username) {
        return expertRepository.findByUsername(username)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
    }

    @Override
    public Expert getByKakaoEmail(String email) {
        return expertRepository.findByEmail(email)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
    }

    @Override
    public boolean existsByEmail(String email) {
        return expertRepository.existsByEmail(email);
    }

}

