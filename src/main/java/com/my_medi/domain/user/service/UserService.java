package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
 * Retrieves a user by their unique UUID.
 *
 * @param uuid the universally unique identifier of the user
 * @return an Optional containing the user if found, or empty if no user exists with the given UUID
 */
Optional<User> findByUserUuid(String uuid);

    /**
 * Persists the given user entity and returns the saved instance.
 *
 * @param user the user entity to be saved
 * @return the saved user entity
 */
User save(User user);

    /**
 * Retrieves a user by their database ID.
 *
 * @param id the unique identifier of the user
 * @return an Optional containing the user if found, or empty if no user exists with the given ID
 */
Optional<User> findById(Long id);

    /**
 * Retrieves a list of all user entities.
 *
 * @return a list containing all users
 */
List<User> findAll();

    /**
 * Deletes the user with the specified database ID.
 *
 * @param id the unique identifier of the user to delete
 */
void deleteById(Long id);

    /**
 * Retrieves a user by their login ID.
 *
 * @param loginId the login ID to search for
 * @return an Optional containing the user if found, or empty if no user matches the login ID
 */
Optional<User> findByLoginId(String loginId);

    /**
 * Retrieves a user by their nickname.
 *
 * @param nickname the nickname to search for
 * @return an Optional containing the user if found, or empty if no user has the given nickname
 */
Optional<User> findByNickname(String nickname);

    /**
 * Checks whether a user exists with the specified nickname.
 *
 * @param nickname the nickname to check for existence
 * @return true if a user with the given nickname exists, false otherwise
 */
boolean existsByNickname(String nickname);

    /**
 * Checks whether a user exists with the specified email address.
 *
 * @param email the email address to check for existence
 * @return true if a user with the given email exists, false otherwise
 */
boolean existsByEmail(String email);
}
