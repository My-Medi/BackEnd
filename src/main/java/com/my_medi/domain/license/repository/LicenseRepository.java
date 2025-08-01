package com.my_medi.domain.license.repository;

import com.my_medi.domain.license.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
    // 커스텀 메서드 예시
    // 특정 날짜 이후 발급된 자격증 조회
    //List<License> findByLicenseDateAfter(LocalDate date);

    // 자격증명으로 검색
    //List<License> findByLicenseNameContaining(String keyword);
}
