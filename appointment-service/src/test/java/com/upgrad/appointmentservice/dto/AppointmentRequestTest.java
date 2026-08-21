package com.upgrad.appointmentservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Appointment Request DTO Tests")
class AppointmentRequestTest {

    private AppointmentRequest appointmentRequest;

    @BeforeEach
    void setUp() {
        appointmentRequest = new AppointmentRequest();
    }

    @Test
    @DisplayName("Should set and get doctorId")
    void testSetGetDoctorId() {
        appointmentRequest.setDoctorId("doc123");
        assertEquals("doc123", appointmentRequest.getDoctorId());
    }

    @Test
    @DisplayName("Should set and get userId")
    void testSetGetUserId() {
        appointmentRequest.setUserId("user123");
        assertEquals("user123", appointmentRequest.getUserId());
    }

    @Test
    @DisplayName("Should set and get appointmentDate")
    void testSetGetAppointmentDate() {
        appointmentRequest.setAppointmentDate("2024-02-15");
        assertEquals("2024-02-15", appointmentRequest.getAppointmentDate());
    }

    @Test
    @DisplayName("Should set and get timeSlot")
    void testSetGetTimeSlot() {
        appointmentRequest.setTimeSlot("10:00 AM");
        assertEquals("10:00 AM", appointmentRequest.getTimeSlot());
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        appointmentRequest.setDoctorId("doc456");
        appointmentRequest.setUserId("user456");
        appointmentRequest.setAppointmentDate("2024-03-20");
        appointmentRequest.setTimeSlot("02:00 PM");

        assertEquals("doc456", appointmentRequest.getDoctorId());
        assertEquals("user456", appointmentRequest.getUserId());
        assertEquals("2024-03-20", appointmentRequest.getAppointmentDate());
        assertEquals("02:00 PM", appointmentRequest.getTimeSlot());
    }

    @Test
    @DisplayName("Should allow modifying fields")
    void testModifyFields() {
        appointmentRequest.setDoctorId("doc1");
        appointmentRequest.setDoctorId("doc2");
        assertEquals("doc2", appointmentRequest.getDoctorId());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        appointmentRequest.setDoctorId(null);
        appointmentRequest.setUserId(null);
        assertNull(appointmentRequest.getDoctorId());
        assertNull(appointmentRequest.getUserId());
    }

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        appointmentRequest.setDoctorId("");
        appointmentRequest.setUserId("");
        assertEquals("", appointmentRequest.getDoctorId());
        assertEquals("", appointmentRequest.getUserId());
    }
}
