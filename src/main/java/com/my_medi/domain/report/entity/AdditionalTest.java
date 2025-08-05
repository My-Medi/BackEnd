package com.my_medi.domain.report.entity;

import jakarta.persistence.*;
import com.my_medi.domain.report.enums.*;

@Entity
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
    private BoneDensityStatus gaitTest;

    @Enumerated(EnumType.STRING)
    private ElderlyPhysicalFunctionStatus elderlyPhysicalFunctionStatus;

    @Embedded
    private ElderlyFunctionTest elderlyFunctionTest;
}

