package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.model.UsernamePasswordModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Payment Service Tests")
class PaymentServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentService paymentService;

    private static final String APPOINTMENT_SERVICE_URL = "http://APPOINTMENT-SERVICE";
    private static final String AUTH_SERVICE_URL = "http://AUTH-SERVICE";
    private static final String APPOINTMENT_ID = "apt123";
    private static final String TOKEN = "Bearer_token_xyz";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(paymentService, "appointmentServiceUrl", APPOINTMENT_SERVICE_URL);
        ReflectionTestUtils.setField(paymentService, "authServiceUrl", AUTH_SERVICE_URL);
    }

    @Test
    @DisplayName("Should receive payment successfully")
    void testReceivePaymentSuccess() {
        // Arrange
        when(restTemplate.postForEntity(
                eq(AUTH_SERVICE_URL + "/oauth/token"),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK));

        when(restTemplate.postForEntity(
                eq(APPOINTMENT_SERVICE_URL + "/payment/" + APPOINTMENT_ID),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("Payment Confirmed", HttpStatus.OK));

        // Act
        paymentService.receivePayment(APPOINTMENT_ID);

        // Assert
        verify(restTemplate, times(2)).postForEntity(anyString(), any(HttpEntity.class), any());
    }

    @Test
    @DisplayName("Should fetch token for payment")
    void testGetTokenForPayment() {
        // Arrange
        when(restTemplate.postForEntity(
                eq(AUTH_SERVICE_URL + "/oauth/token"),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK));

        when(restTemplate.postForEntity(
                eq(APPOINTMENT_SERVICE_URL + "/payment/" + APPOINTMENT_ID),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        // Act
        paymentService.receivePayment(APPOINTMENT_ID);

        // Assert
        verify(restTemplate, atLeastOnce()).postForEntity(anyString(), any(HttpEntity.class), any());
    }

    @Test
    @DisplayName("Should handle payment request with correct appointment ID")
    void testReceivePaymentWithCorrectId() {
        // Arrange
        String testAppointmentId = "apt456";
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        // Act
        paymentService.receivePayment(testAppointmentId);

        // Assert
        verify(restTemplate, times(2)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("Should include authorization header in payment request")
    void testAuthorizationHeaderInPaymentRequest() {
        // Arrange
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        // Act
        paymentService.receivePayment(APPOINTMENT_ID);

        // Assert
        verify(restTemplate, times(2)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("Should handle multiple payment requests")
    void testMultiplePaymentRequests() {
        // Arrange
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        // Act
        paymentService.receivePayment(APPOINTMENT_ID);
        paymentService.receivePayment("apt789");

        // Assert
        verify(restTemplate, times(4)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("Should construct correct appointment payment URL")
    void testCorrectAppointmentPaymentUrl() {
        // Arrange
        String expectedUrl = APPOINTMENT_SERVICE_URL + "/payment/" + APPOINTMENT_ID;
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        // Act
        paymentService.receivePayment(APPOINTMENT_ID);

        // Assert
        verify(restTemplate).postForEntity(contains("/payment/" + APPOINTMENT_ID), any(), any());
    }

    @Test
    @DisplayName("Should handle token retrieval failure gracefully")
    void testTokenRetrievalFailure() {
        // Arrange
        when(restTemplate.postForEntity(
                eq(AUTH_SERVICE_URL + "/oauth/token"),
                any(HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("Auth service unavailable"));

        // Act & Assert
        try {
            paymentService.receivePayment(APPOINTMENT_ID);
        } catch (RuntimeException e) {
            verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
        }
    }

    @Test
    @DisplayName("Should handle payment confirmation failure")
    void testPaymentConfirmationFailure() {
        // Arrange
        when(restTemplate.postForEntity(
                eq(AUTH_SERVICE_URL + "/oauth/token"),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>(TOKEN, HttpStatus.OK));

        when(restTemplate.postForEntity(
                eq(APPOINTMENT_SERVICE_URL + "/payment/" + APPOINTMENT_ID),
                any(HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("Payment service unavailable"));

        // Act & Assert
        try {
            paymentService.receivePayment(APPOINTMENT_ID);
        } catch (RuntimeException e) {
            verify(restTemplate, times(2)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
        }
    }
}
