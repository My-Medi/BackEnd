package com.my_medi.domain.career.service;

import com.my_medi.domain.career.dto.CareerDto;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.career.repository.CareerRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CareerCommandServiceImpl  implements CareerCommandService {

    private final CareerRepository careerRepository;
    private final ExpertRepository expertRepository;

    //단건 등록
    @Override
    public Long registerCareer(Long expertId, CareerDto dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        Career career = Career.builder()
                .companyName(dto.getCompanyName())
                .jobTitle(dto.getJobTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .expert(expert)
                .build();
        careerRepository.save(career);
        return career.getId();
    }

    //여러건 등록
    @Override
    public void registerCareerList(Long expertId, List<CareerDto> dtoList) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        List<Career> careers = dtoList.stream()
                .map(dto -> Career.builder()
                        .companyName(dto.getCompanyName())
                        .jobTitle(dto.getJobTitle())
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .expert(expert)
                        .build())
                .collect(Collectors.toList());

        careerRepository.saveAll(careers);
    }



    @Override
    public Long updateCareer(Long id, CareerDto dto) {
        Career career = careerRepository.findById(id)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        career.update(dto);
        return career.getId();
    }

    @Override
    public Long deleteCareer(Long id) {
        careerRepository.deleteById(id);
        return id;
    }
}

