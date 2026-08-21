package com.upgrad.userservice.service;

import com.upgrad.userservice.model.User;
import com.upgrad.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("User Onboarding Service Tests")
class UserOnboardingServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserOnboardingService userOnboardingService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmailId("john.doe@example.com");
        testUser.setMobile("9876543210");
        testUser.setDob("01-01-1990");
    }

    @Test
    @DisplayName("Should save user details successfully")
    void testSaveUserDetailsSuccess() {
        // Arrange
        User savedUser = new User();
        savedUser.setId("user123");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setEmailId("john.doe@example.com");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userOnboardingService.saveUserDetails(testUser);

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("john.doe@example.com", result.getEmailId());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Should handle null user gracefully")
    void testSaveUserDetailsWithNull() {
        // Arrange
        when(userRepository.save(any())).thenReturn(null);

        // Act
        User result = userOnboardingService.saveUserDetails(testUser);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Should save user with all fields populated")
    void testSaveUserDetailsWithAllFields() {
        // Arrange
        testUser.setId("customId");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User result = userOnboardingService.saveUserDetails(testUser);

        // Assert
        assertNotNull(result);
        assertEquals("customId", result.getId());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    @DisplayName("Should call repository save method exactly once")
    void testRepositoryCallCount() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        userOnboardingService.saveUserDetails(testUser);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }
}
