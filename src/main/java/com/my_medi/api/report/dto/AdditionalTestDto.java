package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.B8Hepatitis;
import com.my_medi.domain.report.entity.ElderlyFunctionTest;
import com.my_medi.domain.report.enums.additionalTest.BoneDensityStatus;
import com.my_medi.domain.report.enums.additionalTest.CognitiveImpairmentStatus;
import com.my_medi.domain.report.enums.additionalTest.DepressionStatus;
import com.my_medi.domain.report.enums.additionalTest.ElderlyPhysicalFunctionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalTestDto {
    private B8Hepatitis b8Hepatitis;

    private DepressionStatus depression;

    private CognitiveImpairmentStatus cognitiveImpairment;

    private BoneDensityStatus boneDensityStatus;

    private ElderlyPhysicalFunctionStatus elderlyPhysicalFunctionStatus;

    private ElderlyFunctionTest elderlyFunctionTest;
}
