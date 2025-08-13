package com.my_medi.infra.gpt.service;

import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.dto.TotalReportData;

public interface OpenAIService {

    HealthReportData parseHealthReport(String imageBase64);

    String ask(String prompt);

    TotalReportData buildTotalReport(Long userId, Integer round);

}
