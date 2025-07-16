package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;

import java.util.List;


public interface ExpertQueryService {

    Expert getExpertById(Long expertId);

    List<Expert> getAllExperts();

    Expert getExpertByUsername(String username);

    Expert getByKakaoEmail(String email);

}
