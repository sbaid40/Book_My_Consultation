package com.upgrad.notificationservice.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Status Enum Tests")
class StatusTest {

    @Test
    @DisplayName("Should have PENDING status")
    void testPendingStatus() {
        assertNotNull(Status.PENDING);
        assertEquals("PENDING", Status.PENDING.name());
    }

    @Test
    @DisplayName("Should have ACTIVE status")
    void testActiveStatus() {
        assertNotNull(Status.ACTIVE);
        assertEquals("ACTIVE", Status.ACTIVE.name());
    }

    @Test
    @DisplayName("Should have REJECTED status")
    void testRejectedStatus() {
        assertNotNull(Status.REJECTED);
        assertEquals("REJECTED", Status.REJECTED.name());
    }

    @Test
    @DisplayName("Should have exactly three status values")
    void testStatusCount() {
        Status[] statuses = Status.values();
        assertEquals(3, statuses.length);
    }

    @Test
    @DisplayName("Should be able to iterate through all statuses")
    void testIterateStatuses() {
        Status[] statuses = Status.values();
        assertTrue(contains(statuses, Status.PENDING));
        assertTrue(contains(statuses, Status.ACTIVE));
        assertTrue(contains(statuses, Status.REJECTED));
    }

    @Test
    @DisplayName("Should be able to compare status values")
    void testStatusComparison() {
        assertEquals(Status.PENDING, Status.PENDING);
        assertNotEquals(Status.PENDING, Status.ACTIVE);
        assertNotEquals(Status.ACTIVE, Status.REJECTED);
    }

    @Test
    @DisplayName("Should convert string to status")
    void testStringToStatus() {
        Status status = Status.valueOf("PENDING");
        assertEquals(Status.PENDING, status);
    }

    @Test
    @DisplayName("Should throw exception for invalid status")
    void testInvalidStatusThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Status.valueOf("INVALID");
        });
    }

    @Test
    @DisplayName("Should have ordinal values")
    void testOrdinalValues() {
        assertEquals(0, Status.PENDING.ordinal());
        assertEquals(1, Status.ACTIVE.ordinal());
        assertEquals(2, Status.REJECTED.ordinal());
    }

    private boolean contains(Status[] statuses, Status target) {
        for (Status status : statuses) {
            if (status == target) {
                return true;
            }
        }
        return false;
    }
}
