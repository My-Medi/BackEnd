package com.my_medi.api.consultation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpertConsultationDto {

    private Long id;
    private String comment;
}
