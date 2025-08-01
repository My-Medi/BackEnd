package com.my_medi.domain.licenseImage.repository;

import com.my_medi.domain.licenseImage.entity.LicenseImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseImageRepository extends JpaRepository<LicenseImage, Long> {
}

