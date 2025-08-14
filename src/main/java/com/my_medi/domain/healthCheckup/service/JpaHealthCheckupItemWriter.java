package com.my_medi.domain.healthCheckup.service;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.repository.HealthCheckupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile({"han"})
@Slf4j
@Component
@RequiredArgsConstructor
public class JpaHealthCheckupItemWriter implements ItemWriter<HealthCheckup> {

    private final HealthCheckupRepository healthCheckupRepository;

    @Override
    public void write(Chunk<? extends HealthCheckup> chunk) throws Exception {
        if (chunk.isEmpty()) {
            return;
        }

        try {
            List<HealthCheckup> toSave = new ArrayList<>();
            int updateCount = 0;

            for (HealthCheckup entity : chunk.getItems()) {
                // 기존 데이터 확인 (subscriberId + year 조합으로)
                var existingEntity = healthCheckupRepository
                        .findBySubscriberIdAndYear(entity.getSubscriberId(), entity.getYear());

                if (existingEntity.isPresent()) {
                    // 기존 데이터 업데이트
                    HealthCheckup existing = existingEntity.get();
                    updateEntityFields(existing, entity);
                    toSave.add(existing);
                    updateCount++;
                } else {
                    // 새로운 데이터 삽입
                    toSave.add(entity);
                }
            }

            // 배치로 저장
            healthCheckupRepository.saveAll(toSave);

            int insertCount = toSave.size() - updateCount;
            log.info("배치 처리 완료 - 처리된 레코드 수: {}, 삽입: {}, 업데이트: {}",
                    chunk.size(), insertCount, updateCount);

        } catch (Exception e) {
            log.error("JPA 배치 쓰기 중 오류 발생", e);
            throw e;
        }
    }

    private void updateEntityFields(HealthCheckup existing, HealthCheckup newEntity) {
        existing.setRegionCode(newEntity.getRegionCode());
        existing.setGender(newEntity.getGender());
        existing.setAgeGroup5yr(newEntity.getAgeGroup5yr());
        existing.setHeight5cm(newEntity.getHeight5cm());
        existing.setWeight5kg(newEntity.getWeight5kg());
        existing.setWaistCm(newEntity.getWaistCm());
        existing.setVisionLeft(newEntity.getVisionLeft());
        existing.setVisionRight(newEntity.getVisionRight());
        existing.setHearingLeft(newEntity.getHearingLeft());
        existing.setHearingRight(newEntity.getHearingRight());
        existing.setSystolicBp(newEntity.getSystolicBp());
        existing.setDiastolicBp(newEntity.getDiastolicBp());
        existing.setFastingBloodSugar(newEntity.getFastingBloodSugar());
        existing.setTotalCholesterol(newEntity.getTotalCholesterol());
        existing.setTriglyceride(newEntity.getTriglyceride());
        existing.setHdlCholesterol(newEntity.getHdlCholesterol());
        existing.setLdlCholesterol(newEntity.getLdlCholesterol());
        existing.setHemoglobin(newEntity.getHemoglobin());
        existing.setUrineProtein(newEntity.getUrineProtein());
        existing.setSerumCreatinine(newEntity.getSerumCreatinine());
        existing.setAst(newEntity.getAst());
        existing.setAlt(newEntity.getAlt());
        existing.setGammaGtp(newEntity.getGammaGtp());
        existing.setSmokingStatus(newEntity.getSmokingStatus());
        existing.setDrinkingStatus(newEntity.getDrinkingStatus());
        existing.setOralExamYn(newEntity.getOralExamYn());
        existing.setToothDecayYn(newEntity.getToothDecayYn());
        existing.setCalculusYn(newEntity.getCalculusYn());
    }
}