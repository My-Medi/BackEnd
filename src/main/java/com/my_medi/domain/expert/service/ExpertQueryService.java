package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ExpertQueryService {

    Expert getExpertById(Long expertId);

    List<Expert> getAllExperts();

    Expert getExpertByUsername(String username);

    Expert getByKakaoEmail(String email);

    boolean existsByEmail(String email);

    Page<Expert> getExpertListByFiltering(List<Specialty> specialtyList, Pageable pageable);

}
