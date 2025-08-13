package com.my_medi.infra.gpt.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalReportData {
    private String nickname;

    // 주요 이상 수치
    private List<String> majorAbnormalItems;

    // 생활 습관 제안
    private  List<String> lifestyleAdvice;

    // 발병 위험순위 TOP3
    private List<RiskItem> top3Risks;

    // 종합 분석 결과
    private String summary;

    // 생활 습관 권장 행동
    private List<String> recommendedActions;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RiskItem {
        private int rank;
        private String title;
        private String indicators;
        private String description;
    }

}
