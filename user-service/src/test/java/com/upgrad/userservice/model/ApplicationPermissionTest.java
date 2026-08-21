package com.upgrad.userservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application Permission Enum Tests")
class ApplicationPermissionTest {

    @Test
    @DisplayName("Should have READ permission")
    void testReadPermission() {
        assertNotNull(ApplicationPermission.READ);
        assertEquals("READ", ApplicationPermission.READ.name());
    }

    @Test
    @DisplayName("Should have WRITE permission")
    void testWritePermission() {
        assertNotNull(ApplicationPermission.WRITE);
        assertEquals("WRITE", ApplicationPermission.WRITE.name());
    }

    @Test
    @DisplayName("Should have exactly two permission values")
    void testPermissionCount() {
        ApplicationPermission[] permissions = ApplicationPermission.values();
        assertEquals(2, permissions.length);
    }

    @Test
    @DisplayName("Should be able to convert string to permission")
    void testStringToPermission() {
        ApplicationPermission permission = ApplicationPermission.valueOf("READ");
        assertEquals(ApplicationPermission.READ, permission);
    }

    @Test
    @DisplayName("Should throw exception for invalid permission")
    void testInvalidPermissionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ApplicationPermission.valueOf("EXECUTE");
        });
    }

    @Test
    @DisplayName("Should have ordinal values")
    void testOrdinalValues() {
        assertEquals(0, ApplicationPermission.READ.ordinal());
        assertEquals(1, ApplicationPermission.WRITE.ordinal());
    }

    @Test
    @DisplayName("Should be comparable")
    void testPermissionComparison() {
        assertEquals(ApplicationPermission.READ, ApplicationPermission.READ);
        assertNotEquals(ApplicationPermission.READ, ApplicationPermission.WRITE);
    }

    @Test
    @DisplayName("Should iterate through all permissions")
    void testIteratePermissions() {
        ApplicationPermission[] permissions = ApplicationPermission.values();
        boolean hasRead = false;
        boolean hasWrite = false;

        for (ApplicationPermission perm : permissions) {
            if (perm == ApplicationPermission.READ) hasRead = true;
            if (perm == ApplicationPermission.WRITE) hasWrite = true;
        }

        assertTrue(hasRead);
        assertTrue(hasWrite);
    }
}
