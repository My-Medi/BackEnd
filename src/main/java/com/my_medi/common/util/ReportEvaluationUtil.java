package com.my_medi.common.util;

import com.my_medi.api.report.dto.AssessmentDto;
import com.my_medi.api.report.dto.AverageComparisonResult;
import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.api.report.dto.ReportResponseDto;

import java.util.Arrays;

import static com.my_medi.api.report.dto.AssessmentDto.*;
import static com.my_medi.api.report.dto.AverageComparisonResult.*;

public final class ReportEvaluationUtil {

    private ReportEvaluationUtil() {}
    

    private static boolean isUnknown(HealthStatus s) {
        return s == null || s == HealthStatus.UNKNOWN;
    }

    private static boolean isSafeOrNormal(HealthStatus s) {
        return s == HealthStatus.SAFE || s == HealthStatus.NORMAL;
    }

    private static boolean isWatch(HealthStatus s) {
        return s == HealthStatus.WATCH;
    }

    private static boolean isBad(HealthStatus s) {
        return s == HealthStatus.CAUTION || s == HealthStatus.DANGER;
    }

    public static void evalAll(ReportResponseDto.ReportResultDto reportResultDto) {
        evalObesity(reportResultDto.getObesityAssessmentDto());
        evalAnemia(reportResultDto.getAnemiaAssessmentDto());
        evalDiabetes(reportResultDto.getDiabetesAssessmentDto());
        evalHypertension(reportResultDto.getHypertensionAssessmentDto());
        evalDyslipidemia(reportResultDto.getDyslipidemiaAssessmentDto());
        evalKidney(reportResultDto.getKidneyDiseaseAssessmentDto());
        evalLiver(reportResultDto.getLiverDiseaseAssessmentDto());
    }

    private static class Counts {
        int safeNormal, watch, bad, unknown;
    }

    private static Counts count(HealthStatus... statuses) {
        Counts c = new Counts();
        for (HealthStatus s : statuses) {
            if (isUnknown(s)) c.unknown++;
            else if (isBad(s)) c.bad++;
            else if (isWatch(s)) c.watch++;
            else if (isSafeOrNormal(s)) c.safeNormal++;
        }
        return c;
    }

    private static boolean allUnknown(HealthStatus... ss) {
        return Arrays.stream(ss).allMatch(ReportEvaluationUtil::isUnknown);
    }

    /* ------------ 질환별 판정 ------------ */

    // 비만: BMI, Waist
    public static void evalObesity(ObesityAssessmentDto dto) {
        HealthStatus bmi = dto.getComparingBmiDto().getHealthStatus();
        HealthStatus waist = dto.getComparingWaistDto().getHealthStatus();

        if (allUnknown(bmi, waist)) { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }

        Counts c = count(bmi, waist);

        if (c.bad >= 1)            { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);  return; } // 1개 이상 경고/위험
        if (c.safeNormal == 2)     { dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); return; } // 2개 모두 안심/정상

        dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR); // 그 외(ATTENTION 조합 등)
    }

    // 고혈압: SBP, DBP
    public static void evalHypertension(HypertensionAssessmentDto dto) {
        HealthStatus sbp = dto.getComparingSystolicBpDto().getHealthStatus();
        HealthStatus dbp = dto.getComparingDiastolicBpDto().getHealthStatus();

        if (allUnknown(sbp, dbp))  { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }

        Counts c = count(sbp, dbp);

        if (c.bad >= 1)            { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);  return; }
        if (c.safeNormal == 2)     { dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); return; }

        dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);
    }

    // 빈혈: Hemoglobin (단일)
    public static void evalAnemia(AnemiaAssessmentDto dto) {
        HealthStatus hb = dto.getComparingHemoglobinDto().getHealthStatus();

        if (isUnknown(hb))         { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }
        if (isBad(hb))             { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);    return; }
        if (isWatch(hb))       { dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);      return; }

        dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); // SAFE/NORMAL
    }

    // 당뇨: Fasting Blood Sugar (단일)
    public static void evalDiabetes(DiabetesAssessmentDto dto) {
        HealthStatus fbs = dto.getComparingFastingBloodSugarDto().getHealthStatus();

        if (isUnknown(fbs))        { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }
        if (isBad(fbs))            { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);    return; }
        if (isWatch(fbs))      { dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);      return; }

        dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD);
    }

    // 이상지질혈증: TC, HDL, LDL, TG (4지표)
    public static void evalDyslipidemia(DyslipidemiaAssessmentDto dto) {
        HealthStatus tc  = dto.getComparingTotalCholesterol().getHealthStatus();
        HealthStatus hdl = dto.getComparingHDL().getHealthStatus();
        HealthStatus tg  = dto.getComparingTriglyceride().getHealthStatus();
        HealthStatus ldl = dto.getComparingLDL().getHealthStatus();

        if (allUnknown(tc, hdl, tg, ldl)) { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }

        Counts c = count(tc, hdl, tg, ldl);

        if (c.bad >= 2)                               { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);  return; } // 2개 이상 경고/위험
        if (c.bad == 0 && c.watch == 0 && c.safeNormal >= 3)
        { dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); return; } // 3개 이상 정상/안심
        if (c.bad == 0 && (c.watch == 1 || c.watch == 2))
        { dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);     return; } // 1~2개 관심 + 나머지 정상이하

        dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR); // 애매 케이스는 유사
    }

    // 신장질환: Serum Creatinine, eGFR (2지표)
    public static void evalKidney(KidneyDiseaseAssessmentDto dto) {
        HealthStatus cr   = dto.getComparingSerumCreatinineDto().getHealthStatus();
        HealthStatus egfr = dto.getComparingEGfr().getHealthStatus();

        if (allUnknown(cr, egfr))  { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }

        Counts c = count(cr, egfr);

        if (c.bad >= 1)            { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);  return; }
        if (c.safeNormal == 2)     { dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); return; }

        dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);
    }

    // 간장질환: AST, ALT, γ-GTP (3지표)
    public static void evalLiver(LiverDiseaseAssessmentDto dto) {
        HealthStatus ast  = dto.getComparingAstDto().getHealthStatus();
        HealthStatus alt  = dto.getComparingAltDto().getHealthStatus();
        HealthStatus ggtp = dto.getComparingGammaGtpDto().getHealthStatus();

        if (allUnknown(ast, alt, ggtp)) { dto.setAverageComparisonResult(AverageComparisonResult.INSUFFICIENT); return; }

        Counts c = count(ast, alt, ggtp);

        if (c.bad >= 2)                            { dto.setAverageComparisonResult(AverageComparisonResult.BELOW_BAD);  return; } // 2개 이상 경고/위험
        if (c.bad == 0 && c.watch == 0 && c.safeNormal >= 2)
        { dto.setAverageComparisonResult(AverageComparisonResult.ABOVE_GOOD); return; } // 2개 이상 정상 + 나머지 안심
        if (c.bad == 0 && c.watch == 1)        { dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);     return; } // 1개 관심 + 나머지 정상이하

        dto.setAverageComparisonResult(AverageComparisonResult.SIMILAR);
    }
}
