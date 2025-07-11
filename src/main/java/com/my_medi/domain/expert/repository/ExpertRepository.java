package com.my_medi.domain.expert.repository;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert,Long> {
}

