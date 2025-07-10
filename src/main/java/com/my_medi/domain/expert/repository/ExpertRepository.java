package com.my_medi.domain.expert.repository;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// MemberRepository 상속
public interface ExpertRepository extends MemberRepository<Expert> {
    //MemberRepository에 없는 Expert 메서드
    Optional<Expert> findByExpertUuid(String expertUuid);
    List<Expert> findBySpecialty(Specialty specialty);

}
