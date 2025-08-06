package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.ImagingTestStatus;
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
public class ImagingTest {
    @Enumerated(EnumType.STRING)
    private ImagingTestStatus imagingTestStatus;
}
