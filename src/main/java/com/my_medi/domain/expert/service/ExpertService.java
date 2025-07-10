package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface ExpertService {

    Expert save(Expert expert);

    Optional<Expert> findById(Long id);

    List<Expert> findAll();

    void deleteById(Long id);

    Optional<Expert> findByLoginId(String loginId);

    Optional<Expert> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Expert> findByExpertUuid(String expertUuid);

    List<Expert> findBySpecialty(Specialty specialty);
}
