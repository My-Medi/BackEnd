package com.my_medi.infra.gpt.service;

import com.my_medi.infra.gpt.dto.HealthReportData;

public interface OpenAIService {

    HealthReportData parseHealthReport(String imageBase64);
}
