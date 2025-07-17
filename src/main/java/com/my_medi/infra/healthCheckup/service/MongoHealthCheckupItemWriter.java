package com.my_medi.infra.healthCheckup.service;

import com.my_medi.infra.healthCheckup.entity.HealthCheckupDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MongoHealthCheckupItemWriter implements ItemWriter<HealthCheckupDocument> {

    private final MongoTemplate mongoTemplate;

    @Override
    public void write(Chunk<? extends HealthCheckupDocument> chunk) throws Exception {
        if (chunk.isEmpty()) {
            return;
        }

        try {
            BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, HealthCheckupDocument.class);

            for (HealthCheckupDocument document : chunk.getItems()) {
                Query query = new Query(Criteria.where("subscriberId").is(document.getSubscriberId())
                        .and("year").is(document.getYear()));

                Update update = new Update()
                        .set("regionCode", document.getRegionCode())
                        .set("gender", document.getGender())
                        .set("ageGroup5yr", document.getAgeGroup5yr())
                        .set("height5cm", document.getHeight5cm())
                        .set("weight5kg", document.getWeight5kg())
                        .set("waistCm", document.getWaistCm())
                        .set("visionLeft", document.getVisionLeft())
                        .set("visionRight", document.getVisionRight())
                        .set("hearingLeft", document.getHearingLeft())
                        .set("hearingRight", document.getHearingRight())
                        .set("systolicBp", document.getSystolicBp())
                        .set("diastolicBp", document.getDiastolicBp())
                        .set("fastingBloodSugar", document.getFastingBloodSugar())
                        .set("totalCholesterol", document.getTotalCholesterol())
                        .set("triglyceride", document.getTriglyceride())
                        .set("hdlCholesterol", document.getHdlCholesterol())
                        .set("ldlCholesterol", document.getLdlCholesterol())
                        .set("hemoglobin", document.getHemoglobin())
                        .set("urineProtein", document.getUrineProtein())
                        .set("serumCreatinine", document.getSerumCreatinine())
                        .set("ast", document.getAst())
                        .set("alt", document.getAlt())
                        .set("gammaGtp", document.getGammaGtp())
                        .set("smokingStatus", document.getSmokingStatus())
                        .set("drinkingStatus", document.getDrinkingStatus())
                        .set("oralExamYn", document.isOralExamYn())
                        .set("toothDecayYn", document.isToothDecayYn())
                        .set("calculusYn", document.isCalculusYn());

                bulkOps.upsert(query, update);
            }

            var result = bulkOps.execute();
            log.info("배치 처리 완료 - 처리된 레코드 수: {}, 삽입: {}, 업데이트: {}",
                    chunk.size(), result.getInsertedCount(), result.getModifiedCount());

        } catch (Exception e) {
            log.error("MongoDB 배치 쓰기 중 오류 발생", e);
            throw e;
        }
    }
}
