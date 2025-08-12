package com.my_medi.domain.report.entity;

import com.my_medi.api.report.dto.ReportPartitionRequestDto;
import com.my_medi.domain.report.enums.additionalTest.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AdditionalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_test_id")
    private Long id;

    @Embedded
    private B8Hepatitis b8Hepatitis;

    @Enumerated(EnumType.STRING)
    private DepressionStatus depression;

    @Enumerated(EnumType.STRING)
    private CognitiveImpairmentStatus cognitiveImpairment;

    @Enumerated(EnumType.STRING)
    private BoneDensityStatus boneDensityStatus;

    @Enumerated(EnumType.STRING)
    private ElderlyPhysicalFunctionStatus elderlyPhysicalFunctionStatus;

    @Embedded
    private ElderlyFunctionTest elderlyFunctionTest;


}

