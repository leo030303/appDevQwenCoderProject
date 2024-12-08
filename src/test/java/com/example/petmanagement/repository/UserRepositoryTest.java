package com.example.petmanagement.repository;

import com.example.petmanagement.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Create test data
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setPassword("password123"); // Assuming the User entity has username and password fields
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("jane_doe");
        user2.setPassword("securepass");
        userRepository.save(user2);
    }

    @Test
    @DisplayName("Test findByUsername: Should return a user when username exists")
    void testFindByUsername_UserExists() {
        User user = userRepository.findByUsername("john_doe");

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("john_doe");
    }

    @Test
    @DisplayName("Test findByUsername: Should return null when username does not exist")
    void testFindByUsername_UserDoesNotExist() {
        User user = userRepository.findByUsername("nonexistent_user");

        assertThat(user).isNull();
    }

    @Test
    @DisplayName("Test save: Should save a new user to the database")
    void testSaveUser() {
        User newUser = new User();
        newUser.setUsername("new_user");
        newUser.setPassword("newpassword");
        userRepository.save(newUser);

        User retrievedUser = userRepository.findByUsername("new_user");

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getUsername()).isEqualTo("new_user");
    }
}
