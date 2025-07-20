package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ReportRequestDto;
import com.my_medi.domain.report.entity.*;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportCommandServiceImpl implements ReportCommandService{
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Override
    public Long writeHealthReport(Long userId, ReportRequestDto reportRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        Report report = Report.builder()
                .user(user)
                .checkupDate(reportRequestDto.getCheckupDate())
                .round(reportRequestDto.getRound())
                .measurement(reportRequestDto.getMeasurement())
                .bloodPressure(reportRequestDto.getBloodPressure())
                .bloodTest(reportRequestDto.getBloodTest())
                .urineTest(reportRequestDto.getUrineTest())
                .imagingTest(reportRequestDto.getImagingTest())
                .interview(reportRequestDto.getInterview())
                .additionalTest(reportRequestDto.getAdditionalTest())
                .build();

        reportRepository.save(report);

        return report.getId();
    }

    @Override
    public Long editHealthReportByRound(Long userId, Integer round, ReportRequestDto reportRequestDto) {
        Report report = reportRepository.findByUserIdAndRound(userId, round)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);

        report.updateCheckupDate(reportRequestDto.getCheckupDate());
        report.updateMeasurement(reportRequestDto.getMeasurement());
        report.updateBloodPressure(reportRequestDto.getBloodPressure());
        report.updateBloodTest(reportRequestDto.getBloodTest());
        report.updateImagingTest(reportRequestDto.getImagingTest());
        report.updateInterview(reportRequestDto.getInterview());
        report.updateAdditionalTest(reportRequestDto.getAdditionalTest());

        return report.getId();
    }
}
