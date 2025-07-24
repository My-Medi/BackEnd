package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.EditReportRequestDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;
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
    public Long writeHealthReport(WriteReportRequestDto writeReportRequestDto) {
        User user = userRepository.findById(writeReportRequestDto.getUserId())
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        Integer nextRound = calculateNextRound(user.getId());

        Report report = Report.builder()
                .round(nextRound)
                .user(user)
                .checkupDate(writeReportRequestDto.getCheckupDate())
                .measurement(ReportConverter.toMeasurement(writeReportRequestDto.getMeasurementDto()))
                .bloodPressure(ReportConverter.toBloodPressure(writeReportRequestDto.getBloodPressureDto()))
                .bloodTest(ReportConverter.toBloodTest(writeReportRequestDto.getBloodTestDto()))
                .urineTest(ReportConverter.toUrineTest(writeReportRequestDto.getUrineTestDto()))
                .imagingTest(ReportConverter.toImagingTest(writeReportRequestDto.getImagingTestDto()))
                .interview(ReportConverter.toInterview(writeReportRequestDto.getInterviewDto()))
                .additionalTest(ReportConverter.toAdditionalTest(writeReportRequestDto.getAdditionalTestDto()))
                .build();

        reportRepository.save(report);

        return report.getId();
    }

    private Integer calculateNextRound(Long userId) {
        return reportRepository.countByUserId(userId) + 1;
    }

    @Override
    public Long editHealthReportByRound(EditReportRequestDto editReportRequestDto) {
        Report report = reportRepository
                .findByUserIdAndRound(editReportRequestDto.getUserId(), editReportRequestDto.getRound())
                .orElseThrow(() -> ReportHandler.NOT_FOUND);

        report.updateCheckupDate(editReportRequestDto.getCheckupDate());
        report.updateMeasurement(ReportConverter.toMeasurement(editReportRequestDto.getMeasurementDto()));
        report.updateBloodPressure(ReportConverter.toBloodPressure(editReportRequestDto.getBloodPressureDto()));
        report.updateBloodTest(ReportConverter.toBloodTest(editReportRequestDto.getBloodTestDto()));
        report.updateUrineTest(ReportConverter.toUrineTest(editReportRequestDto.getUrineTestDto()));
        report.updateImagingTest(ReportConverter.toImagingTest(editReportRequestDto.getImagingTestDto()));
        report.updateInterview(ReportConverter.toInterview(editReportRequestDto.getInterviewDto()));
        report.updateAdditionalTest(ReportConverter.toAdditionalTest(editReportRequestDto.getAdditionalTestDto()));

        return report.getId();
    }
}
