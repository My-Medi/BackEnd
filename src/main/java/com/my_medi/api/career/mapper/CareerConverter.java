package com.my_medi.api.career.mapper;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.entity.Expert;

public class CareerConverter {
    //TODO 삭제하기
    //이유 : CareerRequestDto에 중복 메서드 존재
    public static Career toEntity(CareerRequestDto dto, Expert expert) {
        return Career.builder()
                .companyName(dto.getCompanyName())
                .jobTitle(dto.getJobTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .expert(expert)
                .build();
    }
}


