package com.my_medi.domain.career.service;


import com.my_medi.api.career.dto.CareerResponseDto;

import java.util.List;

public interface CareerCommandService {
    Long registerCareer(Long expertId, CareerResponseDto dto);

    Long updateCareer(Long id, CareerResponseDto dto);

    Long deleteCareer(Long id);

    void registerCareerList(Long expertId, List<CareerResponseDto> dtoList);

}
