package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ReportRequestDto;
import com.my_medi.api.report.mapper.*;
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
    public Long writeHealthReport(Long userId, Integer round, ReportRequestDto reportRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        Report report = Report.builder()
                .round(round)
                .user(user)
                .checkupDate(reportRequestDto.getCheckupDate())
                .measurement(ReportConverter.toMeasurement(reportRequestDto.getMeasurementDto()))
                .bloodPressure(ReportConverter.toBloodPressure(reportRequestDto.getBloodPressureDto()))
                .bloodTest(ReportConverter.toBloodTest(reportRequestDto.getBloodTestDto()))
                .urineTest(ReportConverter.toUrineTest(reportRequestDto.getUrineTestDto()))
                .imagingTest(ReportConverter.toImagingTest(reportRequestDto.getImagingTestDto()))
                .interview(ReportConverter.toInterview(reportRequestDto.getInterviewDto()))
                .additionalTest(ReportConverter.toAdditionalTest(reportRequestDto.getAdditionalTestDto()))
                .build();

        reportRepository.save(report);

        return report.getId();
    }

    @Override
    public Long editHealthReportByRound(Long userId, Integer round, ReportRequestDto reportRequestDto) {
        Report report = reportRepository.findByUserIdAndRound(userId, round)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);

        report.updateCheckupDate(reportRequestDto.getCheckupDate());
        report.updateMeasurement(ReportConverter.toMeasurement(reportRequestDto.getMeasurementDto()));
        report.updateBloodPressure(ReportConverter.toBloodPressure(reportRequestDto.getBloodPressureDto()));
        report.updateBloodTest(ReportConverter.toBloodTest(reportRequestDto.getBloodTestDto()));
        report.updateUrineTest(ReportConverter.toUrineTest(reportRequestDto.getUrineTestDto()));
        report.updateImagingTest(ReportConverter.toImagingTest(reportRequestDto.getImagingTestDto()));
        report.updateInterview(ReportConverter.toInterview(reportRequestDto.getInterviewDto()));
        report.updateAdditionalTest(ReportConverter.toAdditionalTest(reportRequestDto.getAdditionalTestDto()));

        return report.getId();
    }
}
