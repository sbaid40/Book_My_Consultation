package com.upgrad.notificationservice.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Notification Service User Model Tests")
class NotificationUserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetGetId() {
        user.setId("user123");
        assertEquals("user123", user.getId());
    }

    @Test
    @DisplayName("Should set and get firstName")
    void testSetGetFirstName() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }

    @Test
    @DisplayName("Should set and get lastName")
    void testSetGetLastName() {
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    @DisplayName("Should set and get dob")
    void testSetGetDob() {
        user.setDob("01-01-1990");
        assertEquals("01-01-1990", user.getDob());
    }

    @Test
    @DisplayName("Should set and get mobile")
    void testSetGetMobile() {
        user.setMobile("9876543210");
        assertEquals("9876543210", user.getMobile());
    }

    @Test
    @DisplayName("Should set and get emailId")
    void testSetGetEmailId() {
        user.setEmailId("john@example.com");
        assertEquals("john@example.com", user.getEmailId());
    }

    @Test
    @DisplayName("Should set and get createdDate")
    void testSetGetCreatedDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        user.setCreatedDate(date);
        assertEquals(date, user.getCreatedDate());
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        user.setId("user456");
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setDob("15-06-1992");
        user.setMobile("9123456789");
        user.setEmailId("jane@example.com");
        user.setCreatedDate(LocalDate.of(2024, 1, 20));

        assertEquals("user456", user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("9123456789", user.getMobile());
        assertEquals("jane@example.com", user.getEmailId());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        user.setId(null);
        user.setFirstName(null);
        assertNull(user.getId());
        assertNull(user.getFirstName());
    }

    @Test
    @DisplayName("Should modify fields after creation")
    void testModifyFields() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());

        user.setFirstName("Jonathan");
        assertEquals("Jonathan", user.getFirstName());
    }
}
