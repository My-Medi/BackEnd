package com.my_medi.domain.user.repository;

import com.my_medi.domain.member.repository.MemberRepository;
import com.my_medi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//MemberRepository 상속
public interface UserRepository extends MemberRepository<User> {
    /**
 * Retrieves a user by their unique user UUID.
 *
 * @param userUuid the unique identifier of the user
 * @return an {@code Optional} containing the user if found, or empty if not found
 */
    Optional<User> findByUserUuid(String userUuid);
}
