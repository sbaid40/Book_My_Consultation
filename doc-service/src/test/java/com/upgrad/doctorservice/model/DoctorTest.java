package com.upgrad.doctorservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Doctor Model Tests")
class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    @Test
    @DisplayName("Should create doctor with default values")
    void testDoctorDefaultConstructor() {
        assertNotNull(doctor.getId());
        assertEquals("GENERAL_PHYSICIAN", doctor.getSpeciality());
        assertEquals(Status.PENDING, doctor.getStatus());
        assertEquals("Not Rated Yet", doctor.getRating());
        assertEquals(LocalDate.now(), doctor.getRegistrationDate());
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
    @DisplayName("Should set and get emailId")
    void testSetGetEmailId() {
        String email = "john.smith@medical.com";
        doctor.setEmailId(email);
        assertEquals(email, doctor.getEmailId());
    }

    @Test
    @DisplayName("Should set and get mobile")
    void testSetGetMobile() {
        String mobile = "9876543210";
        doctor.setMobile(mobile);
        assertEquals(mobile, doctor.getMobile());
    }

    @Test
    @DisplayName("Should set and get speciality")
    void testSetGetSpeciality() {
        doctor.setSpeciality("CARDIOLOGY");
        assertEquals("CARDIOLOGY", doctor.getSpeciality());
    }

    @Test
    @DisplayName("Should set and get DOB")
    void testSetGetDob() {
        String dob = "1985-06-15";
        doctor.setDob(dob);
        assertEquals(dob, doctor.getDob());
    }

    @Test
    @DisplayName("Should set and get PAN")
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

//    @Test
//    @DisplayName("Should set and get ACTIVEBy")
//    void testSetGetACTIVEBy() {
//        doctor.setACTIVEBy("Admin1");
//        assertEquals("Admin1", doctor.getACTIVEBy());
//    }

    @Test
    @DisplayName("Should set and get approverComments")
    void testSetGetApproverComments() {
        doctor.setApproverComments("ACTIVE");
        assertEquals("ACTIVE", doctor.getApproverComments());
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
    @DisplayName("Should set and get ID")
    void testSetGetId() {
        doctor.setId("doc123");
        assertEquals("doc123", doctor.getId());
    }

//    @Test
//    @DisplayName("Should populate all doctor fields")
//    void testDoctorFieldPopulation() {
//        doctor.setId("doc456");
//        doctor.setFirstName("Dr. Jane");
//        doctor.setLastName("Doe");
//        doctor.setEmailId("jane@hospital.com");
//        doctor.setMobile("9123456789");
//        doctor.setSpeciality("NEUROLOGY");
//        doctor.setDob("1990-03-20");
//        doctor.setPan("XYZAB9876G");
//        doctor.setStatus(Status.ACTIVE);
//        doctor.setACTIVEBy("SuperAdmin");
//        doctor.setApproverComments("Well qualified");
//        doctor.setRating("4.8");
//
//        assertEquals("doc456", doctor.getId());
//        assertEquals("Dr. Jane", doctor.getFirstName());
//        assertEquals("NEUROLOGY", doctor.getSpeciality());
//        assertEquals(Status.ACTIVE, doctor.getStatus());
//        assertEquals("4.8", doctor.getRating());
//    }

    @Test
    @DisplayName("Should generate random ID for new doctors")
    void testRandomIdGeneration() {
        Doctor doc1 = new Doctor();
        Doctor doc2 = new Doctor();

        assertNotNull(doc1.getId());
        assertNotNull(doc2.getId());
        assertNotEquals(doc1.getId(), doc2.getId());
    }

    @Test
    @DisplayName("Should verify toString method")
    void testToString() {
        doctor.setId("docTest");
        doctor.setFirstName("Dr. Test");
        String toString = doctor.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("docTest") || toString.contains("Dr. Test"));
    }

    @Test
    @DisplayName("Should handle status transitions")
    void testStatusTransitions() {
        doctor.setStatus(Status.PENDING);
        assertEquals(Status.PENDING, doctor.getStatus());

        doctor.setStatus(Status.ACTIVE);
        assertEquals(Status.ACTIVE, doctor.getStatus());

        doctor.setStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, doctor.getStatus());
    }

    @Test
    @DisplayName("Should have default speciality as GENERAL_PHYSICIAN")
    void testDefaultSpeciality() {
        assertEquals("GENERAL_PHYSICIAN", doctor.getSpeciality());
    }
}
