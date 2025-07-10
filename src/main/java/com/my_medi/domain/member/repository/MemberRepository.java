package com.my_medi.domain.member.repository;

import com.my_medi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // 사용한 이유 : 인터페이스가 직접 Bean으로 등록되지 않고 UserRepository, ExpertRepository에서 상속해서 사용할 수 있도록
public interface MemberRepository<T extends Member> extends JpaRepository<T, Long> {
    /**
 * Retrieves a member entity by its login ID.
 *
 * @param loginId the login ID to search for
 * @return an Optional containing the member entity if found, or empty if not found
 */
Optional<T> findByLoginId(String loginId);/**
 * Retrieves a member entity by its nickname.
 *
 * @param nickname the nickname of the member to find
 * @return an Optional containing the member if found, or empty if not found
 */
    Optional<T> findByNickname(String nickname);/**
 * Checks whether a member with the specified nickname exists.
 *
 * @param nickname the nickname to check for existence
 * @return true if a member with the given nickname exists, false otherwise
 */
    boolean existsByNickname(String nickname); /**
 * Checks whether a member with the specified email exists.
 *
 * @param email the email address to check for existence
 * @return true if a member with the given email exists, false otherwise
 */
    boolean existsByEmail(String email); // 이 이메일을 가진 회원이 DB에 존재하는지 확인한다.
}
