package com.my_medi.domain.expert.repository.dsl;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.QExpert;
import com.my_medi.domain.expert.entity.Specialty;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ExpertQueryRepositoryImpl implements ExpertQueryRepository{

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Expert> findAllByFiltering(List<Specialty> specialtyList, Pageable pageable) {
        QExpert expert = QExpert.expert;
        // 동적 쿼리 조건 생성
        BooleanExpression condition = createSpecialtyCondition(specialtyList, expert);

        // 카운트 쿼리 (전체 개수 조회)
        long total = queryFactory
                .selectFrom(expert)
                .where(condition)
                .fetchCount();

        // 데이터 조회 쿼리 (페이징 적용)
        List<Expert> experts = queryFactory
                .selectFrom(expert)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(expert.createdDate.desc())
                .fetch();

        return new PageImpl<>(experts, pageable, total);
    }

    // specialty 조건 생성 메서드
    private BooleanExpression createSpecialtyCondition(List<Specialty> specialtyList, QExpert expert) {
        if (specialtyList == null || specialtyList.isEmpty()) {
            return null;
        }

        return expert.specialty.in(specialtyList);
    }

}
