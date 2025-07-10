package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface ExpertService {

    /**
 * Persists the given Expert entity and returns the saved instance.
 *
 * @param expert the Expert entity to be saved
 * @return the saved Expert entity
 */
Expert save(Expert expert);

    /**
 * Retrieves an Expert by its unique identifier.
 *
 * @param id the unique ID of the Expert to retrieve
 * @return an Optional containing the Expert if found, or empty if not found
 */
Optional<Expert> findById(Long id);

    /**
 * Retrieves all Expert entities.
 *
 * @return a list of all Expert instances
 */
List<Expert> findAll();

    /**
 * Deletes the Expert entity with the specified unique identifier.
 *
 * @param id the unique identifier of the Expert to delete
 */
void deleteById(Long id);

    /**
 * Retrieves an Expert by their login ID.
 *
 * @param loginId the login ID of the Expert to find
 * @return an Optional containing the Expert if found, or empty if not found
 */
Optional<Expert> findByLoginId(String loginId);

    /**
 * Retrieves an {@code Expert} entity by its nickname.
 *
 * @param nickname the nickname of the expert to search for
 * @return an {@code Optional} containing the found expert, or empty if no expert with the given nickname exists
 */
Optional<Expert> findByNickname(String nickname);

    /**
 * Determines whether an expert with the specified nickname exists.
 *
 * @param nickname the nickname to check for existence
 * @return true if an expert with the given nickname exists, false otherwise
 */
boolean existsByNickname(String nickname);

    /**
 * Determines whether an expert with the specified email exists.
 *
 * @param email the email address to check for existence
 * @return true if an expert with the given email exists, false otherwise
 */
boolean existsByEmail(String email);

    /**
 * Retrieves an {@code Expert} entity by its unique UUID.
 *
 * @param expertUuid the UUID string identifying the expert
 * @return an {@code Optional} containing the found {@code Expert}, or empty if not found
 */
Optional<Expert> findByExpertUuid(String expertUuid);

    /**
 * Retrieves a list of experts associated with the specified specialty.
 *
 * @param specialty the specialty to filter experts by
 * @return a list of experts matching the given specialty
 */
List<Expert> findBySpecialty(Specialty specialty);
}
