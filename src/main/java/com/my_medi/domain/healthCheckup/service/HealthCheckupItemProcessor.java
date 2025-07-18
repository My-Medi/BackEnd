package com.my_medi.domain.healthCheckup.service;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static com.my_medi.common.util.BatchUtil.*;

@Slf4j
@Component
public class HealthCheckupItemProcessor implements ItemProcessor<String[], HealthCheckup> {

    @Override
    public HealthCheckup process(String[] fields) throws Exception {
        try {
            // 필드 개수 검증 (실제 데이터 기준으로 조정)
            if (fields.length < 30) {
                log.warn("필드 개수가 부족합니다. 예상: 30, 실제: {}", fields.length);
                return null;
            }

            // 필수 필드 검증 (가입자일련번호)
            if (fields[1] == null || fields[1].trim().isEmpty()) {
                log.warn("가입자일련번호가 없는 레코드를 건너뜁니다.");
                return null;
            }

            // 실제 CSV 데이터 패턴에 맞춰 매핑
            HealthCheckup entity = HealthCheckup.builder()
                    .year(parseIntSafely(fields[0]))                    // 기준년도 (2022)
                    .subscriberId(fields[1].trim())                     // 가입자일련번호 (702763)
                    .regionCode(parseIntSafely(fields[2]))              // 시도코드 (41)
                    .gender(fields[3] != null ? fields[3].trim() : "")  // 성별 (1=남, 2=여)
                    .ageGroup5yr(parseIntSafely(fields[4]))             // 연령대코드 (14)
                    .height5cm(parseIntSafely(fields[5]))               // 신장 (160)
                    .weight5kg(parseIntSafely(fields[6]))               // 체중 (75)
                    .waistCm(parseDoubleSafely(fields[7]))              // 허리둘레 (89)
                    .visionLeft(parseDoubleSafely(fields[8]))           // 시력(좌) (1)
                    .visionRight(parseDoubleSafely(fields[9]))          // 시력(우) (1)
                    .hearingLeft(parseIntSafely(fields[10]))            // 청력(좌) (1)
                    .hearingRight(parseIntSafely(fields[11]))           // 청력(우) (1)
                    .systolicBp(parseIntSafely(fields[12]))             // 수축기혈압 (127)
                    .diastolicBp(parseIntSafely(fields[13]))            // 이완기혈압 (73)
                    .fastingBloodSugar(parseIntSafely(fields[14]))      // 식전혈당 (84)
                    .totalCholesterol(parseIntSafely(fields[15]))       // 총콜레스테롤 (빈값 처리)
                    .triglyceride(parseIntSafely(fields[16]))           // 트리글리세라이드 (빈값 처리)
                    .hdlCholesterol(parseIntSafely(fields[17]))         // HDL콜레스테롤 (빈값 처리)
                    .ldlCholesterol(parseIntSafely(fields[18]))         // LDL콜레스테롤 (빈값 처리)
                    .hemoglobin(parseDoubleSafely(fields[19]))          // 혈색소 (14.8)
                    .urineProtein(parseIntSafely(fields[20]))           // 요단백 (1)
                    .serumCreatinine(parseDoubleSafely(fields[21]))     // 혈청크레아티닌 (0.8)
                    .ast(parseIntSafely(fields[22]))                    // AST (25)
                    .alt(parseIntSafely(fields[23]))                    // ALT (20)
                    .gammaGtp(parseIntSafely(fields[24]))               // 감마지티피 (15)
                    .smokingStatus(parseIntSafely(fields[25]))          // 흡연상태 (1)
                    .drinkingStatus(parseIntSafely(fields[26]))         // 음주여부 (0)
                    .oralExamYn(parseBooleanFromInt(fields[27]))        // 구강검진수검여부 (0)
                    .toothDecayYn(parseBooleanFromInt(fields[28]))      // 치아우식증유무 (빈값)
                    .calculusYn(parseBooleanFromInt(fields[29]))        // 치석 (빈값)
                    .build();

            log.debug("데이터 변환 완료 - 가입자: {}, 년도: {}", entity.getSubscriberId(), entity.getYear());
            return entity;

        } catch (Exception e) {
            log.error("데이터 처리 중 오류 발생. 레코드: {}", String.join(",", fields), e);
            return null;
        }
    }


}