package com.my_medi.domain.healthCheckup.repository;

import com.my_medi.infra.healthCheckup.entity.HealthCheckupDocument;
import com.my_medi.domain.healthCheckup.service.HealthCheckupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthCheckupServiceImpl implements HealthCheckupService {

    private final HealthCheckupRepository healthCheckupRepository;

    @Override
    public String saveSampleData() {
        HealthCheckupDocument healthCheckup = HealthCheckupDocument.builder()
                .year(2022)
                .subscriberId("123456789")
                .regionCode(11)
                .gender("M")
                .ageGroup5yr(30)
                .height5cm(170)
                .weight5kg(65)
                .waistCm(82.5)
                .visionLeft(1.0)
                .visionRight(1.0)
                .hearingLeft(1)
                .hearingRight(1)
                .systolicBp(120)
                .diastolicBp(80)
                .fastingBloodSugar(95)
                .totalCholesterol(180)
                .triglyceride(100)
                .hdlCholesterol(55)
                .ldlCholesterol(100)
                .hemoglobin(14.0)
                .urineProtein(1)
                .serumCreatinine(1.0)
                .ast(20)
                .alt(25)
                .gammaGtp(30)
                .smokingStatus(1)
                .drinkingStatus(1)
                .oralExamYn(true)
                .toothDecayYn(false)
                .calculusYn(true)
                .build();

        return healthCheckupRepository.save(healthCheckup).getId();
    }

    @Override
    public List<HealthCheckupDocument> findAll() {
        return healthCheckupRepository.findAll();
    }
}
