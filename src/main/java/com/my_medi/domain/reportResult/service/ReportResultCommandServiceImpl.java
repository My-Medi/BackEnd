package com.my_medi.domain.reportResult.service;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
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
        //age + gender filter
        List<HealthCheckup> healthCheckupList = healthCheckupRepository
                .findByAgeGroupsAndGender(ageGroup5yrRange, gender.getKey());

        int ageGroup10Yr = BirthDateUtil.getAge(report.getUser().getBirthDate());
        Function<HealthCheckup, Double> bmiExtractor = h -> {
            if (h.getHeight5cm() == null || h.getWeight5kg() == null) return null;
            double heightCm = h.getHeight5cm() + 2.5;
            double weightKg = h.getWeight5kg() + 2.5;
            return calculateBmi(weightKg, heightCm);
        };

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

        ReportResult reportResult = ReportResult.builder()
                .report(report)

                // 비만/복부비만
                .averageBmi(calculateAverageBmi(healthCheckupList))
                .percentileBmi(calculatePercentile(healthCheckupList, bmi, bmiExtractor, LOWER))
                .bmiHealthStatus(classifyBmi(bmi))
                .averageWaist(calculateAverage(healthCheckupList, HealthCheckup::getWaistCm))
                .percentileWaist(calculatePercentile(healthCheckupList, waist, HealthCheckup::getWaistCm, PercentileCategory.LOWER))
                .waistHealthStatus(classifyWaistCircumference(waist, gender))

                // 고혈압
                .averageSystolicBp(calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp))
                .percentileSystolicBp(calculatePercentile(healthCheckupList, systolicBp, HealthCheckup::getSystolicBp, LOWER))
                .systolicBpHealthStatus(classifySystolic(systolicBp))
                .averageDiastolicBp(calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp))
                .percentileDiastolicBp(calculatePercentile(healthCheckupList, diastolicBp, HealthCheckup::getDiastolicBp, PercentileCategory.LOWER))
                .diastolicBpHealthStatus(classifyDiastolic(diastolicBp))

                // 빈혈
                .averageHemoglobin(calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin))
                .percentileHemoglobin(calculatePercentile(healthCheckupList, hemoglobin, HealthCheckup::getHemoglobin, UPPER))
                .hemoglobinHealthStatus(classifyHemoglobin(hemoglobin, gender))

                // 당뇨병
                .averageFastingBloodSugar(calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar))
                .percentileFastingBloodSugar(calculatePercentile(healthCheckupList, fastingGlucose, HealthCheckup::getFastingBloodSugar, PercentileCategory.LOWER))
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
                .averageAst(calculateAverage(healthCheckupList, HealthCheckup::getAst))
                .percentileAst(calculatePercentile(healthCheckupList, ast, HealthCheckup::getAst, PercentileCategory.LOWER))
                .astHealthStatus(classifyAST(ast))
                .averageAlt(calculateAverage(healthCheckupList, HealthCheckup::getAlt))
                .percentileAlt(calculatePercentile(healthCheckupList, alt, HealthCheckup::getAlt, PercentileCategory.LOWER))
                .altHealthStatus(classifyALT(alt))
                .averageGammaGtp(calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp))
                .percentileGammaGtp(calculatePercentile(healthCheckupList, gtp, HealthCheckup::getGammaGtp, PercentileCategory.LOWER))
                .gammaGtpHealthStatus(classifyGammaGTP(gtp, gender))

                // 요단백
//                .averageUrineProtein()
//                .percentileProtein(/* Double */)
                .proteinHealthStatus(classifyUrineProtein(report.getUrineTest().getUrineTestStatus()))

                .build();


        return reportResultRepository.save(reportResult).getId();
    }

    @Override
    public void removeReportResult(Long reportId) {
        if(!reportResultRepository.existsByReportId(reportId)) return;
        reportResultRepository.deleteByReportId(reportId);
    }
}
