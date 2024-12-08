package com.example.petmanagement.service;

import com.example.petmanagement.entity.User;
import com.example.petmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "username", "password", "User", false);
    }

    @Test
    @DisplayName("Test createUser: Should save and return the created user")
    void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUsername()).isEqualTo("username");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test updateUserPassword: Should update and return user with new password")
    void testUpdateUserPassword() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUserPassword(1L, "newPassword");

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getPassword()).isEqualTo("newPassword");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test toggleUserUnlockedStatus: Should toggle the unlocked status of the user")
    void testToggleUserUnlockedStatus() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.toggleUserUnlockedStatus(1L);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.isUnlocked()).isFalse();
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test deleteUser: Should delete the user by ID")
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
