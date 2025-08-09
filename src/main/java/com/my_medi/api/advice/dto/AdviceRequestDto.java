package com.my_medi.api.advice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdviceRequestDto {
    private String adviceComment;
}
