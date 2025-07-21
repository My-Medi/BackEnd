package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdditionalTestDto {
    private Boolean needsFurtherTest;
}
