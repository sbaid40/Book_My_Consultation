package com.upgrad.notificationservice.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Notification Service Appointment Model Tests")
class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
    }

    @Test
    @DisplayName("Should set and get appointmentId")
    void testSetGetAppointmentId() {
        appointment.setAppointmentId("apt123");
        assertEquals("apt123", appointment.getAppointmentId());
    }

    @Test
    @DisplayName("Should set and get appointmentDate")
    void testSetGetAppointmentDate() {
        LocalDate date = LocalDate.of(2024, 2, 15);
        appointment.setAppointmentDate(date);
        assertEquals(date, appointment.getAppointmentDate());
    }

    @Test
    @DisplayName("Should set and get createdDate")
    void testSetGetCreatedDate() {
        LocalDateTime now = LocalDateTime.now();
        appointment.setCreatedDate(now);
        assertEquals(now, appointment.getCreatedDate());
    }

    @Test
    @DisplayName("Should set and get doctorId")
    void testSetGetDoctorId() {
        appointment.setDoctorId("doc123");
        assertEquals("doc123", appointment.getDoctorId());
    }

    @Test
    @DisplayName("Should set and get userId")
    void testSetGetUserId() {
        appointment.setUserId("user123");
        assertEquals("user123", appointment.getUserId());
    }

    @Test
    @DisplayName("Should set and get status")
    void testSetGetStatus() {
        appointment.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", appointment.getStatus());
    }

    @Test
    @DisplayName("Should set and get userEmailId")
    void testSetGetUserEmailId() {
        appointment.setUserEmailId("user@example.com");
        assertEquals("user@example.com", appointment.getUserEmailId());
    }

    @Test
    @DisplayName("Should set and get userName")
    void testSetGetUserName() {
        appointment.setUserName("John Doe");
        assertEquals("John Doe", appointment.getUserName());
    }

    @Test
    @DisplayName("Should set and get doctorName")
    void testSetGetDoctorName() {
        appointment.setDoctorName("Dr. Jane Smith");
        assertEquals("Dr. Jane Smith", appointment.getDoctorName());
    }

    @Test
    @DisplayName("Should set and get timeSlot")
    void testSetGetTimeSlot() {
        appointment.setTimeSlot("10:00 AM - 11:00 AM");
        assertEquals("10:00 AM - 11:00 AM", appointment.getTimeSlot());
    }

    @Test
    @DisplayName("Should set and get symptoms")
    void testSetGetSymptoms() {
        appointment.setSymptoms("Fever and cough");
        assertEquals("Fever and cough", appointment.getSymptoms());
    }

    @Test
    @DisplayName("Should set and get priorMedicalHistory")
    void testSetGetPriorMedicalHistory() {
        appointment.setPriorMedicalHistory("Diabetes");
        assertEquals("Diabetes", appointment.getPriorMedicalHistory());
    }

    @Test
    @DisplayName("Should populate all appointment fields")
    void testPopulateAllFields() {
        appointment.setAppointmentId("apt456");
        appointment.setDoctorId("doc456");
        appointment.setUserId("user456");
        appointment.setStatus("PENDING_PAYMENT");
        appointment.setAppointmentDate(LocalDate.of(2024, 3, 10));
        appointment.setCreatedDate(LocalDateTime.now());
        appointment.setUserEmailId("user@test.com");
        appointment.setUserName("Jane");
        appointment.setDoctorName("Dr. Smith");
        appointment.setTimeSlot("02:00 PM");
        appointment.setSymptoms("Headache");
        appointment.setPriorMedicalHistory("None");

        assertEquals("apt456", appointment.getAppointmentId());
        assertEquals("doc456", appointment.getDoctorId());
        assertEquals("PENDING_PAYMENT", appointment.getStatus());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        appointment.setStatus(null);
        appointment.setSymptoms(null);
        assertNull(appointment.getStatus());
        assertNull(appointment.getSymptoms());
    }
}
