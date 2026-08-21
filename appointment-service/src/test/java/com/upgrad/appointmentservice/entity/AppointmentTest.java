package com.upgrad.appointmentservice.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Appointment Entity Tests")
class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
    }

    @Test
    @DisplayName("Should create appointment with default constructor")
    void testAppointmentDefaultConstructor() {
        assertNull(appointment.getAppointmentId());
        assertNull(appointment.getStatus());
    }

    @Test
    @DisplayName("Should set and get appointmentId")
    void testSetGetAppointmentId() {
        String id = "apt123";
        appointment.setAppointmentId(id);
        assertEquals(id, appointment.getAppointmentId());
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
        String doctorId = "doc123";
        appointment.setDoctorId(doctorId);
        assertEquals(doctorId, appointment.getDoctorId());
    }

    @Test
    @DisplayName("Should set and get userId")
    void testSetGetUserId() {
        String userId = "user123";
        appointment.setUserId(userId);
        assertEquals(userId, appointment.getUserId());
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
        String email = "user@example.com";
        appointment.setUserEmailId(email);
        assertEquals(email, appointment.getUserEmailId());
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
    @DisplayName("Should populate appointment using builder")
    void testAppointmentBuilder() {
        LocalDate date = LocalDate.of(2024, 3, 10);
        LocalDateTime now = LocalDateTime.now();

        Appointment apt = Appointment.builder()
                .appointmentId("apt456")
                .doctorId("doc456")
                .userId("user456")
                .status("PENDING_PAYMENT")
                .appointmentDate(date)
                .createdDate(now)
                .userEmailId("user@test.com")
                .userName("Jane")
                .doctorName("Dr. Smith")
                .timeSlot("02:00 PM")
                .symptoms("Headache")
                .priorMedicalHistory("None")
                .build();

        assertEquals("apt456", apt.getAppointmentId());
        assertEquals("doc456", apt.getDoctorId());
        assertEquals("user456", apt.getUserId());
        assertEquals("PENDING_PAYMENT", apt.getStatus());
        assertEquals(date, apt.getAppointmentDate());
        assertEquals(now, apt.getCreatedDate());
    }

    @Test
    @DisplayName("Should populate all appointment fields")
    void testAllAppointmentFields() {
        appointment.setAppointmentId("apt789");
        appointment.setDoctorId("doc789");
        appointment.setUserId("user789");
        appointment.setStatus("CONFIRMED");
        appointment.setAppointmentDate(LocalDate.of(2024, 4, 20));
        appointment.setCreatedDate(LocalDateTime.now());
        appointment.setUserEmailId("jane@example.com");
        appointment.setUserName("Jane Doe");
        appointment.setDoctorName("Dr. John");
        appointment.setTimeSlot("11:00 AM");
        appointment.setSymptoms("Back pain");
        appointment.setPriorMedicalHistory("Asthma");

        assertEquals("apt789", appointment.getAppointmentId());
        assertEquals("CONFIRMED", appointment.getStatus());
        assertEquals("jane@example.com", appointment.getUserEmailId());
        assertEquals("Dr. John", appointment.getDoctorName());
    }

    @Test
    @DisplayName("Should verify toString method")
    void testToString() {
        appointment.setAppointmentId("apt999");
        String toString = appointment.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("apt999") || toString.contains("Appointment"));
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        appointment.setStatus(null);
        appointment.setSymptoms(null);
        assertNull(appointment.getStatus());
        assertNull(appointment.getSymptoms());
    }

    @Test
    @DisplayName("Should update appointment status")
    void testUpdateStatus() {
        appointment.setStatus("PENDING_PAYMENT");
        assertEquals("PENDING_PAYMENT", appointment.getStatus());

        appointment.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", appointment.getStatus());
    }
}
