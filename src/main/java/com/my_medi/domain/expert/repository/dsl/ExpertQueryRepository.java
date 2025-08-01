package com.my_medi.domain.expert.repository.dsl;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpertQueryRepository {

    Page<Expert> findAllByFiltering(List<Specialty> specialtyList, Pageable pageable);
}
