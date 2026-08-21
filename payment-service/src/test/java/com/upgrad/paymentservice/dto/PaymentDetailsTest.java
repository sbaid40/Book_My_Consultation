package com.upgrad.paymentservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Payment Details DTO Tests")
class PaymentDetailsTest {

    private PaymentDetails paymentDetails;

    @BeforeEach
    void setUp() {
        paymentDetails = new PaymentDetails();
    }

    @Test
    @DisplayName("Should set and get id")
    void testSetGetId() {
        paymentDetails.setId("payment123");
        assertEquals("payment123", paymentDetails.getId());
    }

    @Test
    @DisplayName("Should set and get appointmentId")
    void testSetGetAppointmentId() {
        paymentDetails.setAppointmentId("apt123");
        assertEquals("apt123", paymentDetails.getAppointmentId());
    }

    @Test
    @DisplayName("Should set and get createdDate")
    void testSetGetCreatedDate() {
        String date = "2024-02-15T10:30:00";
        paymentDetails.setCreatedDate(date);
        assertEquals(date, paymentDetails.getCreatedDate());
    }

    @Test
    @DisplayName("Should have default createdDate")
    void testDefaultCreatedDate() {
        assertNotNull(paymentDetails.getCreatedDate());
        assertTrue(paymentDetails.getCreatedDate().contains("202"));
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        paymentDetails.setId("pay456");
        paymentDetails.setAppointmentId("apt456");
        paymentDetails.setCreatedDate("2024-03-20T14:00:00");

        assertEquals("pay456", paymentDetails.getId());
        assertEquals("apt456", paymentDetails.getAppointmentId());
        assertEquals("2024-03-20T14:00:00", paymentDetails.getCreatedDate());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        paymentDetails.setId(null);
        paymentDetails.setAppointmentId(null);
        assertNull(paymentDetails.getId());
        assertNull(paymentDetails.getAppointmentId());
    }

    @Test
    @DisplayName("Should handle empty strings")
    void testEmptyStrings() {
        paymentDetails.setId("");
        paymentDetails.setAppointmentId("");
        assertEquals("", paymentDetails.getId());
        assertEquals("", paymentDetails.getAppointmentId());
    }

    @Test
    @DisplayName("Should modify fields after creation")
    void testModifyFields() {
        paymentDetails.setId("initial");
        assertEquals("initial", paymentDetails.getId());

        paymentDetails.setId("modified");
        assertEquals("modified", paymentDetails.getId());
    }
}
