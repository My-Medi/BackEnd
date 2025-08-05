package com.my_medi.domain.career.service;

import com.my_medi.api.career.dto.CareerRequestDto;

import java.util.List;

public interface CareerCommandService {
    Long registerCareer(Long expertId, CareerRequestDto dto);

    Long updateCareer(Long id, CareerRequestDto dto);

    Long deleteCareer(Long id);

    void registerCareerList(Long expertId, List<CareerRequestDto> dtoList);

}
