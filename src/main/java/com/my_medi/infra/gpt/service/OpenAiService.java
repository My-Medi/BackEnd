package com.my_medi.infra.gpt.service;

import com.my_medi.infra.gpt.dto.HealthReportData;

public interface OpenAiService {

    HealthReportData parseHealthReport(String imageBase64);
}
