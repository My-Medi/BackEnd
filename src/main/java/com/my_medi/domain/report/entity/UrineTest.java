package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.UrineTestStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrineTest {
    @Enumerated(EnumType.STRING)
    private UrineTestStatus urineTestStatus;
}
