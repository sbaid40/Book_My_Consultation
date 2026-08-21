package com.upgrad.notificationservice.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Notification Service Doctor Model Tests")
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetGetId() {
        doctor.setId("doc123");
        assertEquals("doc123", doctor.getId());
    }

    @Test
    @DisplayName("Should set and get firstName")
    void testSetGetFirstName() {
        doctor.setFirstName("Dr. John");
        assertEquals("Dr. John", doctor.getFirstName());
    }

    @Test
    @DisplayName("Should set and get lastName")
    void testSetGetLastName() {
        doctor.setLastName("Smith");
        assertEquals("Smith", doctor.getLastName());
    }

    @Test
    @DisplayName("Should set and get speciality")
    void testSetGetSpeciality() {
        doctor.setSpeciality("CARDIOLOGY");
        assertEquals("CARDIOLOGY", doctor.getSpeciality());
    }

    @Test
    @DisplayName("Should set and get dob")
    void testSetGetDob() {
        doctor.setDob("1985-06-15");
        assertEquals("1985-06-15", doctor.getDob());
    }

    @Test
    @DisplayName("Should set and get mobile")
    void testSetGetMobile() {
        doctor.setMobile("9876543210");
        assertEquals("9876543210", doctor.getMobile());
    }

    @Test
    @DisplayName("Should set and get emailId")
    void testSetGetEmailId() {
        doctor.setEmailId("john@hospital.com");
        assertEquals("john@hospital.com", doctor.getEmailId());
    }

    @Test
    @DisplayName("Should set and get pan")
    void testSetGetPan() {
        doctor.setPan("ABCDE1234F");
        assertEquals("ABCDE1234F", doctor.getPan());
    }

    @Test
    @DisplayName("Should set and get status")
    void testSetGetStatus() {
        doctor.setStatus(Status.ACTIVE);
        assertEquals(Status.ACTIVE, doctor.getStatus());
    }

    @Test
    @DisplayName("Should set and get approvedBy")
    void testSetGetApprovedBy() {
        doctor.setApprovedBy("Admin1");
        assertEquals("Admin1", doctor.getApprovedBy());
    }

    @Test
    @DisplayName("Should set and get approverComments")
    void testSetGetApproverComments() {
        doctor.setApproverComments("Approved");
        assertEquals("Approved", doctor.getApproverComments());
    }

    @Test
    @DisplayName("Should set and get registrationDate")
    void testSetGetRegistrationDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        doctor.setRegistrationDate(date);
        assertEquals(date, doctor.getRegistrationDate());
    }

    @Test
    @DisplayName("Should set and get verificationDate")
    void testSetGetVerificationDate() {
        LocalDate date = LocalDate.of(2024, 1, 20);
        doctor.setVerificationDate(date);
        assertEquals(date, doctor.getVerificationDate());
    }

    @Test
    @DisplayName("Should set and get rating")
    void testSetGetRating() {
        doctor.setRating("4.5");
        assertEquals("4.5", doctor.getRating());
    }

    @Test
    @DisplayName("Should populate all doctor fields")
    void testPopulateAllFields() {
        doctor.setId("doc456");
        doctor.setFirstName("Dr. Jane");
        doctor.setLastName("Doe");
        doctor.setEmailId("jane@hospital.com");
        doctor.setMobile("9123456789");
        doctor.setSpeciality("NEUROLOGY");
        doctor.setDob("1990-03-20");
        doctor.setPan("XYZAB9876G");
        doctor.setStatus(Status.ACTIVE);
        doctor.setApprovedBy("SuperAdmin");
        doctor.setApproverComments("Well qualified");
        doctor.setRating("4.8");

        assertEquals("doc456", doctor.getId());
        assertEquals("Dr. Jane", doctor.getFirstName());
        assertEquals("NEUROLOGY", doctor.getSpeciality());
        assertEquals(Status.ACTIVE, doctor.getStatus());
        assertEquals("4.8", doctor.getRating());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        doctor.setId(null);
        doctor.setFirstName(null);
        assertNull(doctor.getId());
        assertNull(doctor.getFirstName());
    }

    @Test
    @DisplayName("Should handle status transitions")
    void testStatusTransitions() {
        doctor.setStatus(Status.PENDING);
        assertEquals(Status.PENDING, doctor.getStatus());

        doctor.setStatus(Status.ACTIVE);
        assertEquals(Status.ACTIVE, doctor.getStatus());
    }
}
