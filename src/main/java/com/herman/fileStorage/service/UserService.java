package com.herman.fileStorage.service;

import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service class for User operations
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Register a new user with hashed password
     *
     * @param username the username of the new user
     * @param passwordString the password in plain text
     * @return the saved User
     * @throws IllegalArgumentException if username is already taken
     */
    public User registerUser(String username, String passwordString) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(passwordString);
        User newUser = new User(UUID.randomUUID(), username, encodedPassword);
        return userRepository.save(newUser);
    }

    /**
     * Finds a user by their username
     *
     * @param username the username to find
     * @return Optional containing the User if found, else empty
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Authenticates a user by theiir username and password
     *
     * @param username the username
     * @param password the password in plain text
     * @return the authenticated user
     * @throws IllegalArgumentException if username or password are invalid
     */
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        return user;
    }

    /**
     * Finds a user by ID.
     *
     * @param id the UUID of the user
     * @return Optional containing the User if found, else empty
     */
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}
