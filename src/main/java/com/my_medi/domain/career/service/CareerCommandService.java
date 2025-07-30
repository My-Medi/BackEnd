package com.my_medi.domain.career.service;

import com.my_medi.domain.career.dto.CareerDto;

import java.util.List;

public interface CareerCommandService {
    Long registerCareer(Long expertId, CareerDto dto);

    Long updateCareer(Long id, CareerDto dto);

    Long deleteCareer(Long id);

    void registerCareerList(Long expertId, List<CareerDto> dtoList);

}
