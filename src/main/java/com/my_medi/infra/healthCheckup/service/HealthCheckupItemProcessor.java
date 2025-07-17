package com.my_medi.infra.healthCheckup.service;

import com.my_medi.infra.healthCheckup.entity.HealthCheckupDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static com.my_medi.common.util.BatchUtil.*;

@Slf4j
@Component
public class HealthCheckupItemProcessor implements ItemProcessor<String[], HealthCheckupDocument> {

    @Override
    public HealthCheckupDocument process(String[] fields) throws Exception {
        try {
            // 필드 개수 검증
            if (fields.length != 30) {
                log.warn("필드 개수가 맞지 않습니다. 예상: 30, 실제: {}", fields.length);
                return null;
            }

            // 필수 필드 검증 (가입자일련번호)
            if (fields[1] == null || fields[1].trim().isEmpty()) {
                log.warn("가입자일련번호가 없는 레코드를 건너뜁니다.");
                return null;
            }

            return HealthCheckupDocument.builder()
                    .year(parseIntSafely(fields[0]))                    // 기준년도
                    .subscriberId(fields[1].trim())                     // 가입자일련번호
                    .regionCode(parseIntSafely(fields[2]))              // 시도코드
                    .gender(fields[3] != null ? fields[3].trim() : "")  // 성별
                    .ageGroup5yr(parseIntSafely(fields[4]))             // 연령대코드(5세단위)
                    .height5cm(parseIntSafely(fields[5]))               // 신장(5cm단위)
                    .weight5kg(parseIntSafely(fields[6]))               // 체중(5kg단위)
                    .waistCm(parseDoubleSafely(fields[7]))              // 허리둘레
                    .visionLeft(parseDoubleSafely(fields[8]))           // 시력(좌)
                    .visionRight(parseDoubleSafely(fields[9]))          // 시력(우)
                    .hearingLeft(parseIntSafely(fields[10]))            // 청력(좌)
                    .hearingRight(parseIntSafely(fields[11]))           // 청력(우)
                    .systolicBp(parseIntSafely(fields[12]))             // 수축기혈압
                    .diastolicBp(parseIntSafely(fields[13]))            // 이완기혈압
                    .fastingBloodSugar(parseIntSafely(fields[14]))      // 식전혈당(공복혈당)
                    .totalCholesterol(parseIntSafely(fields[15]))       // 총콜레스테롤
                    .triglyceride(parseIntSafely(fields[16]))           // 트리글리세라이드
                    .hdlCholesterol(parseIntSafely(fields[17]))         // HDL콜레스테롤
                    .ldlCholesterol(parseIntSafely(fields[18]))         // LDL콜레스테롤
                    .hemoglobin(parseDoubleSafely(fields[19]))          // 혈색소
                    .urineProtein(parseIntSafely(fields[20]))           // 요단백
                    .serumCreatinine(parseDoubleSafely(fields[21]))     // 혈청크레아티닌
                    .ast(parseIntSafely(fields[22]))                    // 혈청지오티(AST)
                    .alt(parseIntSafely(fields[23]))                    // 혈청지피티(ALT)
                    .gammaGtp(parseIntSafely(fields[24]))               // 감마지티피
                    .smokingStatus(parseIntSafely(fields[25]))          // 흡연상태
                    .drinkingStatus(parseIntSafely(fields[26]))         // 음주여부
                    .oralExamYn(parseBooleanSafely(fields[27]))         // 구강검진수검여부
                    .toothDecayYn(parseBooleanSafely(fields[28]))       // 치아우식증유무
                    .calculusYn(parseBooleanSafely(fields[29]))         // 치석
                    .build();

        } catch (Exception e) {
            log.error("데이터 처리 중 오류 발생. 필드: {}", String.join(",", fields), e);
            return null;
        }
    }


}