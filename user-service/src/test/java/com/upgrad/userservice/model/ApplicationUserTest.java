package com.upgrad.userservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application User Model Tests")
class ApplicationUserTest {

    private ApplicationUser applicationUser;
    private Set<GrantedAuthority> authorities;

    @BeforeEach
    void setUp() {
        authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("READ"));
    }

    @Test
    @DisplayName("Should build ApplicationUser with all parameters")
    void testBuildApplicationUser() {
        // Arrange & Act
        applicationUser = ApplicationUser.builder()
                .username("testuser")
                .password("password123")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        // Assert
        assertNotNull(applicationUser);
        assertEquals("testuser", applicationUser.getUsername());
        assertEquals("password123", applicationUser.getPassword());
        assertTrue(applicationUser.isEnabled());
        assertTrue(applicationUser.isAccountNonLocked());
    }

    @Test
    @DisplayName("Should return correct username")
    void testGetUsername() {
        applicationUser = ApplicationUser.builder()
                .username("john_doe")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertEquals("john_doe", applicationUser.getUsername());
    }

    @Test
    @DisplayName("Should return correct password")
    void testGetPassword() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("encrypted_password")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertEquals("encrypted_password", applicationUser.getPassword());
    }

    @Test
    @DisplayName("Should return authorities")
    void testGetAuthorities() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertNotNull(applicationUser.getAuthorities());
        assertEquals(2, applicationUser.getAuthorities().size());
    }

    @Test
    @DisplayName("Should implement UserDetails interface")
    void testUserDetailsImplementation() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertTrue(applicationUser instanceof org.springframework.security.core.userdetails.UserDetails);
    }

    @Test
    @DisplayName("Should return account non-expired correctly")
    void testAccountNonExpired() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertTrue(applicationUser.isAccountNonExpired());
    }

    @Test
    @DisplayName("Should return account non-locked correctly")
    void testAccountNonLocked() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertTrue(applicationUser.isAccountNonLocked());
    }

    @Test
    @DisplayName("Should return credentials non-expired correctly")
    void testCredentialsNonExpired() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertTrue(applicationUser.isCredentialsNonExpired());
    }

    @Test
    @DisplayName("Should return enabled status correctly")
    void testIsEnabled() {
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertTrue(applicationUser.isEnabled());
    }

    @Test
    @DisplayName("Should handle disabled user")
    void testDisabledUser() {
        applicationUser = ApplicationUser.builder()
                .username("disabled_user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(false)
                .build();

        assertFalse(applicationUser.isEnabled());
    }

    @Test
    @DisplayName("Should handle expired account")
    void testExpiredAccount() {
        applicationUser = ApplicationUser.builder()
                .username("expired_user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertFalse(applicationUser.isAccountNonExpired());
    }

    @Test
    @DisplayName("Should handle locked account")
    void testLockedAccount() {
        applicationUser = ApplicationUser.builder()
                .username("locked_user")
                .password("pass")
                .authorities(authorities)
                .isAccountExpired(false)
                .isAccountNonLocked(false)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertFalse(applicationUser.isAccountNonLocked());
    }

    @Test
    @DisplayName("Should handle empty authorities")
    void testEmptyAuthorities() {
        Set<GrantedAuthority> emptyAuthorities = new HashSet<>();
        applicationUser = ApplicationUser.builder()
                .username("user")
                .password("pass")
                .authorities(emptyAuthorities)
                .isAccountExpired(false)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        assertNotNull(applicationUser.getAuthorities());
        assertEquals(0, applicationUser.getAuthorities().size());
    }
}
