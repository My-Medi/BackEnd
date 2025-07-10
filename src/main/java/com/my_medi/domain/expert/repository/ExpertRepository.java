package com.my_medi.domain.expert.repository;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// MemberRepository 상속
public interface ExpertRepository extends MemberRepository<Expert> {
    /**
 * Retrieves an Expert entity by its unique expert UUID.
 *
 * @param expertUuid the unique identifier of the expert
 * @return an Optional containing the Expert if found, or empty if not found
 */
    Optional<Expert> findByExpertUuid(String expertUuid);
    /**
 * Retrieves a list of experts who have the specified specialty.
 *
 * @param specialty the specialty to filter experts by
 * @return a list of experts matching the given specialty
 */
List<Expert> findBySpecialty(Specialty specialty);

}
