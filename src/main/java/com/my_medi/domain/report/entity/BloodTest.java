package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodTest {
    private Double hemoglobin;      //혈색소
    private Boolean anemia;

    private Integer fastingGlucose; //공복혈당
    private Boolean diabetes;

    private Integer totalCholesterol;
    private Integer hdl;            //고밀도 콜레스트롤
    private Integer triglyceride;   //중성지방
    private Integer ldl;            //저밀도 콜레스트롤
    private Boolean hyperlipidemia; //고지혈증

    private Double creatinine; // ex: 0.9
    private Integer egfr;       //신사과체여과율

    private Integer ast;
    private Integer alt;
    private Integer gtp;        //감마지티피
}