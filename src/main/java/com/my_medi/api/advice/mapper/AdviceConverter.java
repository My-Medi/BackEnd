package com.my_medi.api.advice.mapper;

import com.my_medi.api.advice.dto.AdviceResponseDto.AdviceSimplePageResponse;
import com.my_medi.api.advice.dto.AdviceResponseDto.AdviceDto;
import com.my_medi.domain.advice.entity.Advice;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class AdviceConverter {

    // Entity -> DTO
    public static AdviceDto fromAdvice(Advice advice) {
        return AdviceDto.builder()
                .adviceId(advice.getId())
                .userId(advice.getUser().getId())
                .expertId(advice.getExpert().getId())
                .adviceComment(advice.getAdviceComment())
                .createdDate(advice.getCreatedDate())
                .build();
    }

    public static AdviceSimplePageResponse toAdviceSimplePageResponse(Page<Advice> advicePage) {
        List<AdviceDto> adviceList = advicePage.getContent().stream()
                .map(AdviceConverter::fromAdvice)
                .collect(Collectors.toList());

        return new AdviceSimplePageResponse(
                adviceList,
                advicePage.getTotalPages(),
                advicePage.getNumber()
        );
    }
}
