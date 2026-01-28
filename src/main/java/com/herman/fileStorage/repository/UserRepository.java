package com.herman.fileStorage.repository;

import com.herman.fileStorage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repo inteface for User Entity
 * Provides CRUD operations and query methods
 */

public interface UserRepository extends JpaRepository<User,UUID> {

    /**
     * Finds a users by their username
     *
     * @param username the username to search for
     * @returns an optional containing thje User if found, else empty
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the username does exist
     *
     * @param username the username to check for
     * @return true if a user with the username exists, else false
     */
    boolean existsByUsername(String username);
}
