package com.upgrad.userservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application Role Enum Tests")
class ApplicationRoleTest {

    @Test
    @DisplayName("Should have USER role")
    void testUserRole() {
        assertNotNull(ApplicationRole.USER);
        assertEquals("USER", ApplicationRole.USER.name());
    }

    @Test
    @DisplayName("Should have DOCTOR role")
    void testDoctorRole() {
        assertNotNull(ApplicationRole.DOCTOR);
        assertEquals("DOCTOR", ApplicationRole.DOCTOR.name());
    }

    @Test
    @DisplayName("Should have ADMIN role")
    void testAdminRole() {
        assertNotNull(ApplicationRole.ADMIN);
        assertEquals("ADMIN", ApplicationRole.ADMIN.name());
    }

    @Test
    @DisplayName("Should have exactly three role values")
    void testRoleCount() {
        ApplicationRole[] roles = ApplicationRole.values();
        assertEquals(3, roles.length);
    }

    @Test
    @DisplayName("Should get authorities for USER role")
    void testUserRoleAuthorities() {
        Set<SimpleGrantedAuthority> authorities = ApplicationRole.USER.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.size() > 0);
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    @DisplayName("Should get authorities for DOCTOR role")
    void testDoctorRoleAuthorities() {
        Set<SimpleGrantedAuthority> authorities = ApplicationRole.DOCTOR.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.size() > 0);
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR")));
    }

    @Test
    @DisplayName("Should get authorities for ADMIN role")
    void testAdminRoleAuthorities() {
        Set<SimpleGrantedAuthority> authorities = ApplicationRole.ADMIN.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.size() > 0);
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    @DisplayName("Should convert string to role")
    void testStringToRole() {
        ApplicationRole role = ApplicationRole.valueOf("USER");
        assertEquals(ApplicationRole.USER, role);
    }

    @Test
    @DisplayName("Should throw exception for invalid role")
    void testInvalidRoleThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ApplicationRole.valueOf("INVALID_ROLE");
        });
    }

    @Test
    @DisplayName("Should have ordinal values")
    void testOrdinalValues() {
        assertEquals(0, ApplicationRole.USER.ordinal());
        assertEquals(1, ApplicationRole.DOCTOR.ordinal());
        assertEquals(2, ApplicationRole.ADMIN.ordinal());
    }

    @Test
    @DisplayName("Should be comparable")
    void testRoleComparison() {
        assertEquals(ApplicationRole.USER, ApplicationRole.USER);
        assertNotEquals(ApplicationRole.USER, ApplicationRole.DOCTOR);
    }

    @Test
    @DisplayName("All roles should have permissions")
    void testAllRolesHavePermissions() {
        for (ApplicationRole role : ApplicationRole.values()) {
            assertNotNull(role.getAuthorities());
            assertTrue(role.getAuthorities().size() > 0);
        }
    }
}
