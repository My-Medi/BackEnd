package com.my_medi.domain.member.repository;

import com.my_medi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // 사용한 이유 : 인터페이스가 직접 Bean으로 등록되지 않고 UserRepository, ExpertRepository에서 상속해서 사용할 수 있도록
public interface MemberRepository<T extends Member> extends JpaRepository<T, Long> {
    Optional<T> findByLoginId(String loginId);//loginId(아이디)로 회원 찾기
    Optional<T> findByNickname(String nickname);//nickname으로 회원을 찾는다.
    boolean existsByNickname(String nickname); // 회원가입 시 닉네임 중복 체크할 때 사용
    boolean existsByEmail(String email); // 이 이메일을 가진 회원이 DB에 존재하는지 확인한다.
}
