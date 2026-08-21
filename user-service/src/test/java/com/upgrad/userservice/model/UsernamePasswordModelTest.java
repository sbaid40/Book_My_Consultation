package com.upgrad.userservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Username Password Model Tests")
class UsernamePasswordModelTest {

    private UsernamePasswordModel model;

    @BeforeEach
    void setUp() {
        model = new UsernamePasswordModel();
    }

    @Test
    @DisplayName("Should set and get username")
    void testSetGetUsername() {
        model.setUsername("testuser");
        assertEquals("testuser", model.getUsername());
    }

    @Test
    @DisplayName("Should set and get password")
    void testSetGetPassword() {
        model.setPassword("password123");
        assertEquals("password123", model.getPassword());
    }

    @Test
    @DisplayName("Should populate both fields")
    void testPopulateBothFields() {
        model.setUsername("user@example.com");
        model.setPassword("securePass123");

        assertEquals("user@example.com", model.getUsername());
        assertEquals("securePass123", model.getPassword());
    }

    @Test
    @DisplayName("Should handle null username")
    void testNullUsername() {
        model.setUsername(null);
        assertNull(model.getUsername());
    }

    @Test
    @DisplayName("Should handle null password")
    void testNullPassword() {
        model.setPassword(null);
        assertNull(model.getPassword());
    }

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        model.setUsername("");
        model.setPassword("");
        assertEquals("", model.getUsername());
        assertEquals("", model.getPassword());
    }

    @Test
    @DisplayName("Should modify credentials")
    void testModifyCredentials() {
        model.setUsername("user1");
        model.setPassword("pass1");
        assertEquals("user1", model.getUsername());

        model.setUsername("user2");
        model.setPassword("pass2");
        assertEquals("user2", model.getUsername());
        assertEquals("pass2", model.getPassword());
    }

    @Test
    @DisplayName("Should handle special characters")
    void testSpecialCharacters() {
        model.setUsername("user@domain.com");
        model.setPassword("P@ssw0rd!#$");
        assertEquals("user@domain.com", model.getUsername());
        assertEquals("P@ssw0rd!#$", model.getPassword());
    }

    @Test
    @DisplayName("Should handle long credentials")
    void testLongCredentials() {
        String longUsername = "this_is_a_very_long_username_for_testing@domain.com";
        String longPassword = "thisIsAVeryLongPasswordWithManyCharacters123456789";

        model.setUsername(longUsername);
        model.setPassword(longPassword);

        assertEquals(longUsername, model.getUsername());
        assertEquals(longPassword, model.getPassword());
    }
}
