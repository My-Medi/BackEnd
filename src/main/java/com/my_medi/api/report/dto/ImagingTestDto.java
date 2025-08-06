package com.my_medi.api.report.dto;

import com.my_medi.domain.report.enums.ImagingTestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagingTestDto {
    private ImagingTestStatus imagingTestStatus;
}
