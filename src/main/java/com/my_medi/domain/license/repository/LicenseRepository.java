package com.my_medi.domain.license.repository;

import com.my_medi.domain.license.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
    // 커스텀 메서드
}
