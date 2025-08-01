package com.my_medi.api.expert.dto;

import com.my_medi.domain.expert.entity.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SpecialtyDto {
    private Specialty specialty;
    private String key;
}
