package com.my_medi.infra.gpt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthTermResponse {
    private String term;               // 용어
    private String shortDesc; //용어 한 줄 요약
    private String description; // 2문장으로 요약
    private String normalRange; // 정상범위
}
