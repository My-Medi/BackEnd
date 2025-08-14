package com.my_medi.domain.reportResult.service;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.entity.StatMetric;
import com.my_medi.domain.healthCheckup.repository.HealthCheckupRepository;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.domain.reportResult.repository.ReportResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

import static com.my_medi.common.consts.StaticVariable.E_GFR_AVERAGE;
import static com.my_medi.common.util.HealthMetricCalculator.*;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.*;
import static com.my_medi.common.util.HealthMetricCalculator.calculateBmi;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportResultCommandServiceImpl implements ReportResultCommandService{
    private final ReportResultRepository reportResultRepository;
    private final ReportRepository reportRepository;
    private final HealthCheckupRepository healthCheckupRepository;
    @Override
    public Long calculateReportResult(Long reportId, String birthDate, Gender gender) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);
        List<Integer> ageGroup5yrRange = BirthDateUtil
                .getAgeGroup5yrRange(BirthDateUtil.getAge(birthDate));


        int ageGroup10Yr = BirthDateUtil.getAge(report.getUser().getBirthDate());

        // 사용자 수치
        Double bmi = report.getMeasurement().getBmi();
        Double waist = report.getMeasurement().getWaist();
        Integer diastolicBp = report.getBloodPressure().getDiastolic();
        Integer systolicBp = report.getBloodPressure().getSystolic();
        Double hemoglobin = report.getBloodTest().getHemoglobin();
        Integer fastingGlucose = report.getBloodTest().getFastingGlucose();
        Integer ast = report.getBloodTest().getAst();
        Integer alt = report.getBloodTest().getAlt();
        Integer hdl = report.getBloodTest().getHdl();
        Integer gtp = report.getBloodTest().getGtp();
        Integer total_cholesterol = report.getBloodTest().getTotalCholesterol();
        Integer ldl = report.getBloodTest().getLdl();
        Integer egfr = report.getBloodTest().getEgfr();
        Integer triglyceride = report.getBloodTest().getTriglyceride();
        Double creatinine = report.getBloodTest().getCreatinine();

        // === 여기서부터 DB 집계 호출 ===
        double avgBmi = healthCheckupRepository.avgBmi(ageGroup5yrRange, gender.getKey());
        double pctBmi = (bmi == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.BMI, ageGroup5yrRange, gender.getKey(), bmi, PercentileCategory.LOWER);

        double avgWaist = healthCheckupRepository.avg(StatMetric.WAIST, ageGroup5yrRange, gender.getKey());
        double pctWaist = (waist == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.WAIST, ageGroup5yrRange, gender.getKey(), waist, PercentileCategory.LOWER);

        double avgSys = healthCheckupRepository.avg(StatMetric.SYSTOLIC, ageGroup5yrRange, gender.getKey());
        double pctSys = (systolicBp == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.SYSTOLIC, ageGroup5yrRange, gender.getKey(), systolicBp, PercentileCategory.LOWER);

        double avgDia = healthCheckupRepository.avg(StatMetric.DIASTOLIC, ageGroup5yrRange, gender.getKey());
        double pctDia = (diastolicBp == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.DIASTOLIC, ageGroup5yrRange, gender.getKey(), diastolicBp, PercentileCategory.LOWER);

        double avgHemo = healthCheckupRepository.avg(StatMetric.HEMOGLOBIN, ageGroup5yrRange, gender.getKey());
        double pctHemo = (hemoglobin == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.HEMOGLOBIN, ageGroup5yrRange, gender.getKey(), hemoglobin, PercentileCategory.UPPER);

        double avgFbs = healthCheckupRepository.avg(StatMetric.FASTING_GLUCOSE, ageGroup5yrRange, gender.getKey());
        double pctFbs = (fastingGlucose == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.FASTING_GLUCOSE, ageGroup5yrRange, gender.getKey(), fastingGlucose, PercentileCategory.LOWER);

        double avgAst = healthCheckupRepository.avg(StatMetric.AST, ageGroup5yrRange, gender.getKey());
        double pctAst = (ast == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.AST, ageGroup5yrRange, gender.getKey(), ast, PercentileCategory.LOWER);

        double avgAlt = healthCheckupRepository.avg(StatMetric.ALT, ageGroup5yrRange, gender.getKey());
        double pctAlt = (alt == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.ALT, ageGroup5yrRange, gender.getKey(), alt, PercentileCategory.LOWER);

        double avgGtp = healthCheckupRepository.avg(StatMetric.GTP, ageGroup5yrRange, gender.getKey());
        double pctGtp = (gtp == null) ? 0.0
                : healthCheckupRepository.percentileAgainstTarget(StatMetric.GTP, ageGroup5yrRange, gender.getKey(), gtp, PercentileCategory.LOWER);

        ReportResult reportResult = ReportResult.builder()
                .report(report)

                // 비만/복부비만
                .averageBmi(avgBmi)
                .percentileBmi(pctBmi)
                .bmiHealthStatus(classifyBmi(bmi))
                .averageWaist(avgWaist)
                .percentileWaist(pctWaist)
                .waistHealthStatus(classifyWaistCircumference(waist, gender))

                // 고혈압
                .averageSystolicBp(avgSys)
                .percentileSystolicBp(pctSys)
                .systolicBpHealthStatus(classifySystolic(systolicBp))
                .averageDiastolicBp(avgDia)
                .percentileDiastolicBp(pctDia)
                .diastolicBpHealthStatus(classifyDiastolic(diastolicBp))

                // 빈혈
                .averageHemoglobin(avgHemo)
                .percentileHemoglobin(pctHemo)
                .hemoglobinHealthStatus(classifyHemoglobin(hemoglobin, gender))

                // 당뇨병
                .averageFastingBloodSugar(avgFbs)
                .percentileFastingBloodSugar(pctFbs)
                .fastingBloodSugarHealthStatus(classifyFastingGlucose(fastingGlucose))

                // 이상지질혈증
                .averageTotalCholesterol(provideAverageTotalCholesterol(ageGroup10Yr))
//                .percentileTotalCholesterol(/* Double */)
                .totalCholesterolHealthStatus(classifyTotalCholesterol(total_cholesterol))
                .averageHdl(provideAverageHDL(ageGroup10Yr))
//                .percentileHdl(/* Double */)
                .hdlHealthStatus(classifyHDL(hdl))
                .averageLdl(provideAverageLDL(ageGroup10Yr))
//                .percentileLdl(/* Double */)
                .ldlHealthStatus(classifyLDL(ldl))
                .averageTriglyceride(provideAverageTriglyceride(ageGroup10Yr))
//                .percentileTriglyceride(/* Double */)
                .triglycerideHealthStatus(classifyTriglycerides(triglyceride))

                // 신장질환
                .averageSerumCreatine(0.8)
//                .percentileSerumCreatine(/* Double */)
                .creatineHealthStatus(classifyCreatinine(creatinine, gender))
                .averageEGfr(E_GFR_AVERAGE)
//                .percentileEGfr(/* Double */)
                .eGfrHealthStatus(classifyE_GFR(egfr))

                // 간장질환
                .averageAst(avgAst)
                .percentileAst(pctAst)
                .astHealthStatus(classifyAST(ast))
                .averageAlt(avgAlt)
                .percentileAlt(pctAlt)
                .altHealthStatus(classifyALT(alt))
                .averageGammaGtp(avgGtp)
                .percentileGammaGtp(pctGtp)
                .gammaGtpHealthStatus(classifyGammaGTP(gtp, gender))

                // 요단백
//                .averageUrineProtein()
//                .percentileProtein(/* Double */)
                .proteinHealthStatus(classifyUrineProtein(report.getUrineTest().getUrineTestStatus()))

                .build();

        reportResult.calculateTotalScore();


        return reportResultRepository.save(reportResult).getId();
    }

    @Override
    public void removeReportResult(Long reportId) {
        if(!reportResultRepository.existsByReportId(reportId)) return;
        reportResultRepository.deleteByReportId(reportId);
    }
}
