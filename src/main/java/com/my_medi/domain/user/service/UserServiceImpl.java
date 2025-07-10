package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a user by their UUID.
     *
     * @param uuid the UUID of the user to find
     * @return an {@code Optional} containing the user if found, or empty if not found
     */
    @Override
    public Optional<User> findByUserUuid(String uuid) {
        return userRepository.findByUserUuid(uuid);
    }

    /**
     * Persists the given user entity and returns the saved instance.
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their database ID.
     *
     * @param id the unique identifier of the user
     * @return an {@code Optional} containing the user if found, or empty if not present
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves all user entities from the repository.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Deletes the user with the specified ID from the repository.
     *
     * @param id the unique identifier of the user to delete
     */
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Retrieves a user by their login ID.
     *
     * @param loginId the login ID to search for
     * @return an {@code Optional} containing the user if found, or empty if not found
     */
    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    /**
     * Retrieves a user by their nickname.
     *
     * @param nickname the nickname to search for
     * @return an {@code Optional} containing the user if found, or empty if not found
     */
    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    /**
     * Checks if a user exists with the specified nickname.
     *
     * @param nickname the nickname to check for existence
     * @return true if a user with the given nickname exists, false otherwise
     */
    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /**
     * Checks whether a user exists with the specified email address.
     *
     * @param email the email address to check for existence
     * @return true if a user with the given email exists, false otherwise
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
