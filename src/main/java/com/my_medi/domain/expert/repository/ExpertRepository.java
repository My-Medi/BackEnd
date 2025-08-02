package com.my_medi.domain.expert.repository;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpertRepository extends JpaRepository<Expert,Long> {
    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmail(String email);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"careers"})
    Optional<Expert> findWithCareersById(Long id);

    @EntityGraph(attributePaths = {"licenseImages"})
    Optional<Expert> findWithLicenseImagesById(Long id);
}


