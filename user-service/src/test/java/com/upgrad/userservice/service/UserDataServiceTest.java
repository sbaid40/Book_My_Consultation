package com.upgrad.userservice.service;

import com.upgrad.userservice.exception.RequestedResourceNotFoundException;
import com.upgrad.userservice.model.User;
import com.upgrad.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("User Data Service Tests")
class UserDataServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDataService userDataService;

    private User testUser;
    private static final String USER_ID = "user123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(USER_ID);
        testUser.setFirstName("Jane");
        testUser.setLastName("Smith");
        testUser.setEmailId("jane.smith@example.com");
        testUser.setMobile("9123456789");
        testUser.setDob("15-06-1992");
    }

    @Test
    @DisplayName("Should find user by ID successfully")
    void testFindUserByIdSuccess() {
        // Arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(testUser));

        // Act
        User result = userDataService.findUserById(USER_ID);

        // Assert
        assertNotNull(result);
        assertEquals(USER_ID, result.getId());
        assertEquals("Jane", result.getFirstName());
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void testFindUserByIdNotFound() {
        // Arrange
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RequestedResourceNotFoundException.class,
                     () -> userDataService.findUserById(USER_ID));
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    @DisplayName("Should throw exception for null user ID")
    void testFindUserByNullId() {
        // Arrange
        when(userRepository.findById(null)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RequestedResourceNotFoundException.class,
                     () -> userDataService.findUserById(null));
    }

    @Test
    @DisplayName("Should find user with complete details")
    void testFindUserWithCompleteDetails() {
        // Arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(testUser));

        // Act
        User result = userDataService.findUserById(USER_ID);

        // Assert
        assertNotNull(result);
        assertEquals("jane.smith@example.com", result.getEmailId());
        assertEquals("9123456789", result.getMobile());
        assertEquals("15-06-1992", result.getDob());
    }

    @Test
    @DisplayName("Should call repository findById exactly once")
    void testRepositoryCallCount() {
        // Arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(testUser));

        // Act
        userDataService.findUserById(USER_ID);

        // Assert
        verify(userRepository, times(1)).findById(USER_ID);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Should throw exception for non-existent ID")
    void testFindUserNonExistentId() {
        // Arrange
        String nonExistentId = "nonExistent123";
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RequestedResourceNotFoundException.class,
                     () -> userDataService.findUserById(nonExistentId));
        verify(userRepository, times(1)).findById(nonExistentId);
    }
}
