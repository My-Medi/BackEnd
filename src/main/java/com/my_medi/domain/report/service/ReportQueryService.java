package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ComparingReportResponseDto;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.user.entity.User;

public interface ReportQueryService {
    Report getReportByRound(Long userId, Integer round);

    ComparingReportResponseDto compareReport(User user, Integer round);

<<<<<<< HEAD
    Report getLatestReportByUserId(Long userId);

=======
<<<<<<< HEAD
>>>>>>> 0440ed4 ([MEDI-81] feat: create latest user report's summay API)
    long getReportCountByUser(User user);
=======
    Report getLatestReportByUserId(Long userId);
>>>>>>> f20fa20 ([MEDI-81] feat: create latest user report's summay API)
}
