package com.my_medi.domain.expert.repository;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpertRepository extends JpaRepository<Expert,Long> {
    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT e FROM Expert e LEFT JOIN FETCH e.careers WHERE e.id = :id")
    Optional<Expert> findWithCareersById(@Param("id") Long id);

    @Query("SELECT e FROM Expert e LEFT JOIN FETCH e.licenseImages WHERE e.id = :id")
    Optional<Expert> findWithLicenseImagesById(@Param("id") Long id);

    @Query("SELECT e FROM Expert e LEFT JOIN FETCH e.licenses WHERE e.id = :id")
    Optional<Expert> findWithLicensesById(@Param("id") Long id);

}


