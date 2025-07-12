package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;

import java.util.List;
import java.util.UUID;

public interface ExpertQueryService {

    Expert getExpertById(Long expertId);

    List<Expert> getAllExperts();
    Expert getExpertByUsername(UUID username);

}
