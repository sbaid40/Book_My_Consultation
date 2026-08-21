package com.upgrad.ratingservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Doctor Rating Model Tests")
class DoctorRatingTest {

    private DoctorRating doctorRating;

    @BeforeEach
    void setUp() {
        doctorRating = new DoctorRating();
    }

    @Test
    @DisplayName("Should set and get doctorId")
    void testSetGetDoctorId() {
        doctorRating.setDoctorId("doc123");
        assertEquals("doc123", doctorRating.getDoctorId());
    }

    @Test
    @DisplayName("Should set and get rating")
    void testSetGetRating() {
        doctorRating.setRating(4);
        assertEquals(4, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should set and get numberOfPeopleRated")
    void testSetGetNumberOfPeopleRated() {
        doctorRating.setNumberOfPeopleRated(10);
        assertEquals(10, doctorRating.getNumberOfPeopleRated());
    }

    @Test
    @DisplayName("Should set and get totalRating")
    void testSetGetTotalRating() {
        doctorRating.setTotalRating(40);
        assertEquals(40, doctorRating.getTotalRating());
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        doctorRating.setDoctorId("doc456");
        doctorRating.setRating(5);
        doctorRating.setNumberOfPeopleRated(20);
        doctorRating.setTotalRating(100);

        assertEquals("doc456", doctorRating.getDoctorId());
        assertEquals(5, doctorRating.getRating());
        assertEquals(20, doctorRating.getNumberOfPeopleRated());
        assertEquals(100, doctorRating.getTotalRating());
    }

    @Test
    @DisplayName("Should handle minimum rating value")
    void testMinimumRating() {
        doctorRating.setRating(1);
        assertEquals(1, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should handle maximum rating value")
    void testMaximumRating() {
        doctorRating.setRating(5);
        assertEquals(5, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should handle zero rating")
    void testZeroRating() {
        doctorRating.setRating(0);
        assertEquals(0, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should handle zero numberOfPeopleRated")
    void testZeroNumberOfPeopleRated() {
        doctorRating.setNumberOfPeopleRated(0);
        assertEquals(0, doctorRating.getNumberOfPeopleRated());
    }

    @Test
    @DisplayName("Should handle negative rating values")
    void testNegativeRating() {
        doctorRating.setRating(-1);
        assertEquals(-1, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should modify rating value")
    void testModifyRating() {
        doctorRating.setRating(3);
        assertEquals(3, doctorRating.getRating());

        doctorRating.setRating(4);
        assertEquals(4, doctorRating.getRating());
    }

    @Test
    @DisplayName("Should create new DoctorRating instance")
    void testNewInstance() {
        DoctorRating rating2 = new DoctorRating();
        assertNotEquals(rating2, doctorRating);
        assertNull(rating2.getDoctorId());
    }

    @Test
    @DisplayName("Should handle large numberOfPeopleRated")
    void testLargeNumberOfPeopleRated() {
        doctorRating.setNumberOfPeopleRated(1000000);
        assertEquals(1000000, doctorRating.getNumberOfPeopleRated());
    }

    @Test
    @DisplayName("Should handle large totalRating")
    void testLargeTotalRating() {
        doctorRating.setTotalRating(5000000);
        assertEquals(5000000, doctorRating.getTotalRating());
    }

    @Test
    @DisplayName("Should calculate average rating correctly")
    void testAverageRatingCalculation() {
        doctorRating.setDoctorId("doc789");
        doctorRating.setRating(3);
        doctorRating.setNumberOfPeopleRated(5);
        doctorRating.setTotalRating(15);

        // Average = 15 / 5 = 3
        int average = doctorRating.getTotalRating() / doctorRating.getNumberOfPeopleRated();
        assertEquals(3, average);
    }
}
