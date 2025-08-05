package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ComparingReportResponseDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.repository.HealthCheckupRepository;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportQueryServiceImpl implements ReportQueryService{
    private final ReportRepository reportRepository;
    private final HealthCheckupRepository healthCheckupRepository;

    @Override
    public Report getReportByRound(Long userId, Integer round) {
        return reportRepository.findByUserIdAndRound(userId, round)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);
    }

    @Override
    public ComparingReportResponseDto compareReport(User user, Integer round) {
        Report report = reportRepository.findByUserIdAndRound(user.getId(), round)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);
        //find ageGrouped10Yr
        List<Integer> ageGroup5yrRange = BirthDateUtil
                .getAgeGroup5yrRange(BirthDateUtil.getAge(user.getBirthDate()));
        //age + gender filter
        List<HealthCheckup> healthCheckupList = healthCheckupRepository
                .findByAgeGroupsAndGender(ageGroup5yrRange, user.getGender().getKey());

        return ReportConverter.toComparingReportResponseDto(healthCheckupList, report);
    }

    @Override
    public long getReportCountByUser(User user) {
        return reportRepository.countByUserId(user.getId());
    }
}
