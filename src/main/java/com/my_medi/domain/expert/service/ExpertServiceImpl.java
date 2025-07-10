package com.my_medi.domain.expert.service;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    /**
     * Persists the given Expert entity and returns the saved instance.
     *
     * @param expert the Expert entity to be saved
     * @return the saved Expert entity
     */
    @Override
    public Expert save(Expert expert) {
        return expertRepository.save(expert);
    }

    /**
     * Retrieves an Expert entity by its unique identifier.
     *
     * @param id the unique identifier of the Expert
     * @return an Optional containing the Expert if found, or empty if not present
     */
    @Override
    public Optional<Expert> findById(Long id) {
        return expertRepository.findById(id);
    }

    /**
     * Retrieves all Expert entities from the repository.
     *
     * @return a list of all Expert entities
     */
    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    /**
     * Deletes the expert entity with the specified ID.
     *
     * @param id the unique identifier of the expert to delete
     */
    @Override
    public void deleteById(Long id) {
        expertRepository.deleteById(id);
    }

    /**
     * Retrieves an expert by their login ID.
     *
     * @param loginId the login ID of the expert to find
     * @return an {@code Optional} containing the found expert, or empty if no expert matches the login ID
     */
    @Override
    public Optional<Expert> findByLoginId(String loginId) {
        return expertRepository.findByLoginId(loginId);
    }

    /**
     * Retrieves an expert by their nickname.
     *
     * @param nickname the nickname of the expert to search for
     * @return an {@code Optional} containing the expert if found, or empty if not found
     */
    @Override
    public Optional<Expert> findByNickname(String nickname) {
        return expertRepository.findByNickname(nickname);
    }

    /**
     * Checks whether an expert exists with the specified nickname.
     *
     * @param nickname the nickname to check for existence
     * @return true if an expert with the given nickname exists, false otherwise
     */
    @Override
    public boolean existsByNickname(String nickname) {
        return expertRepository.existsByNickname(nickname);
    }

    /**
     * Checks whether an expert exists with the specified email address.
     *
     * @param email the email address to check for existence
     * @return true if an expert with the given email exists, false otherwise
     */
    @Override
    public boolean existsByEmail(String email) {
        return expertRepository.existsByEmail(email);
    }

    /**
     * Retrieves an {@code Expert} entity by its UUID.
     *
     * @param expertUuid the UUID of the expert to find
     * @return an {@code Optional} containing the found expert, or empty if not found
     */
    @Override
    public Optional<Expert> findByExpertUuid(String expertUuid) {
        return expertRepository.findByExpertUuid(expertUuid);
    }

    /**
     * Retrieves a list of experts who have the specified specialty.
     *
     * @param specialty the specialty to filter experts by
     * @return a list of experts matching the given specialty
     */
    @Override
    public List<Expert> findBySpecialty(Specialty specialty) {
        return expertRepository.findBySpecialty(specialty);
    }
}
