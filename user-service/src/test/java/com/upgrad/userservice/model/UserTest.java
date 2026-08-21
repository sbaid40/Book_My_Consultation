package com.upgrad.userservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Model Tests")
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Should create user with default values")
    void testUserDefaultConstructor() {
        assertNotNull(user.getId());
        assertFalse(user.isHasRatedDoctor());
        assertEquals(LocalDate.now(), user.getCreatedDate());
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
    @DisplayName("Should set and get emailId")
    void testSetGetEmailId() {
        String email = "john.doe@example.com";
        user.setEmailId(email);
        assertEquals(email, user.getEmailId());
    }

    @Test
    @DisplayName("Should set and get mobile")
    void testSetGetMobile() {
        String mobile = "9876543210";
        user.setMobile(mobile);
        assertEquals(mobile, user.getMobile());
    }

    @Test
    @DisplayName("Should set and get DOB")
    void testSetGetDob() {
        String dob = "01-01-1990";
        user.setDob(dob);
        assertEquals(dob, user.getDob());
    }

    @Test
    @DisplayName("Should set and get ID")
    void testSetGetId() {
        String id = "user123";
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    @DisplayName("Should set hasRatedDoctor flag")
    void testSetGetHasRatedDoctor() {
        user.setHasRatedDoctor(true);
        assertTrue(user.isHasRatedDoctor());

        user.setHasRatedDoctor(false);
        assertFalse(user.isHasRatedDoctor());
    }

    @Test
    @DisplayName("Should set and get createdDate")
    void testSetGetCreatedDate() {
        LocalDate testDate = LocalDate.of(2024, 1, 15);
        user.setCreatedDate(testDate);
        assertEquals(testDate, user.getCreatedDate());
    }

    @Test
    @DisplayName("Should populate all user fields")
    void testUserFieldPopulation() {
        user.setId("testId123");
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setEmailId("jane@example.com");
        user.setMobile("9123456789");
        user.setDob("15-06-1992");
        user.setHasRatedDoctor(true);

        assertEquals("testId123", user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane@example.com", user.getEmailId());
        assertEquals("9123456789", user.getMobile());
        assertEquals("15-06-1992", user.getDob());
        assertTrue(user.isHasRatedDoctor());
    }

    @Test
    @DisplayName("Should generate random ID for new users")
    void testRandomIdGeneration() {
        User user1 = new User();
        User user2 = new User();

        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    @DisplayName("Should have non-empty ID")
    void testIdNotEmpty() {
        assertNotNull(user.getId());
        assertNotEquals("", user.getId());
    }

    @Test
    @DisplayName("Should set created date to current date by default")
    void testCreatedDateDefault() {
        LocalDate now = LocalDate.now();
        assertEquals(now, user.getCreatedDate());
    }

    @Test
    @DisplayName("Should modify user email")
    void testModifyEmail() {
        user.setEmailId("original@example.com");
        assertEquals("original@example.com", user.getEmailId());

        user.setEmailId("modified@example.com");
        assertEquals("modified@example.com", user.getEmailId());
    }

    @Test
    @DisplayName("Should handle special characters in name")
    void testSpecialCharactersInName() {
        user.setFirstName("O'Brien");
        user.setLastName("van der Berg");
        assertEquals("O'Brien", user.getFirstName());
        assertEquals("van der Berg", user.getLastName());
    }
}
