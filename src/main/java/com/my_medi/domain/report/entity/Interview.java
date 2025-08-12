package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.interview.LifestyleHabitsStatus;
import com.my_medi.domain.report.enums.interview.PositiveNegativeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    @Enumerated(EnumType.STRING)
    private PositiveNegativeStatus hasPastDisease;
    @Enumerated(EnumType.STRING)
    private PositiveNegativeStatus onMedication;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "life_style_habits_status",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.STRING)
    private Set<LifestyleHabitsStatus> lifestyleHabitsStatusList = new HashSet<>();
}