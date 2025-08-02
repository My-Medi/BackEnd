package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;


public interface ExpertQueryService {

    Expert getExpertById(Long expertId);

    List<Expert> getAllExperts();

    Expert getExpertByUsername(String username);

    Expert getByKakaoEmail(String email);

    boolean existsByEmail(String email);

    Expert getExpertWithResume(Long expertId);
}
