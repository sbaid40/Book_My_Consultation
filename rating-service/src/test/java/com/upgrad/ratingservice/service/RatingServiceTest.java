package com.upgrad.ratingservice.service;

import com.upgrad.ratingservice.exception.RatingValueInvalidException;
import com.upgrad.ratingservice.model.DoctorRating;
import com.upgrad.ratingservice.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Rating Service Tests")
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private RatingService ratingService;

    private DoctorRating testRating;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testRating = new DoctorRating();
        testRating.setDoctorId("doc123");
        testRating.setRating(4);
    }

    @Test
    @DisplayName("Should rate doctor successfully for new doctor")
    void testRateDoctorNewDoctor() throws IOException {
        // Arrange
        when(ratingRepository.findByDoctorId("doc123")).thenReturn(null);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(testRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        DoctorRating result = ratingService.rateDoctor(testRating);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getNumberOfPeopleRated());
        verify(ratingRepository, times(1)).findByDoctorId("doc123");
        verify(ratingRepository, times(1)).save(any(DoctorRating.class));
        verify(messageService, times(1)).produceMessage(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should rate doctor successfully for existing doctor")
    void testRateDoctorExistingDoctor() throws IOException {
        // Arrange
        DoctorRating existingRating = new DoctorRating();
        existingRating.setDoctorId("doc123");
        existingRating.setRating(3);
        existingRating.setNumberOfPeopleRated(5);
        existingRating.setTotalRating(15);

        when(ratingRepository.findByDoctorId("doc123")).thenReturn(existingRating);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(existingRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        DoctorRating result = ratingService.rateDoctor(testRating);

        // Assert
        assertNotNull(result);
        assertEquals(6, result.getNumberOfPeopleRated());
        verify(ratingRepository, times(1)).findByDoctorId("doc123");
        verify(ratingRepository, times(1)).save(existingRating);
    }

    @Test
    @DisplayName("Should throw exception for invalid rating (zero)")
    void testRateDoctorInvalidRatingZero() {
        // Arrange
        testRating.setRating(0);

        // Act & Assert
        assertThrows(RatingValueInvalidException.class,
                     () -> ratingService.rateDoctor(testRating));
    }

    @Test
    @DisplayName("Should throw exception for invalid rating (negative)")
    void testRateDoctorInvalidRatingNegative() {
        // Arrange
        testRating.setRating(-1);

        // Act & Assert
        assertThrows(RatingValueInvalidException.class,
                     () -> ratingService.rateDoctor(testRating));
    }

    @Test
    @DisplayName("Should throw exception for rating greater than 5")
    void testRateDoctorInvalidRatingTooHigh() {
        // Arrange
        testRating.setRating(6);

        // Act & Assert
        assertThrows(RatingValueInvalidException.class,
                     () -> ratingService.rateDoctor(testRating));
    }

    @Test
    @DisplayName("Should accept rating value of 1")
    void testRateDoctorMinimumValid() throws IOException {
        // Arrange
        testRating.setRating(1);
        when(ratingRepository.findByDoctorId("doc123")).thenReturn(null);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(testRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        DoctorRating result = ratingService.rateDoctor(testRating);

        // Assert
        assertNotNull(result);
        verify(ratingRepository, times(1)).save(any(DoctorRating.class));
    }

    @Test
    @DisplayName("Should accept rating value of 5")
    void testRateDoctorMaximumValid() throws IOException {
        // Arrange
        testRating.setRating(5);
        when(ratingRepository.findByDoctorId("doc123")).thenReturn(null);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(testRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        DoctorRating result = ratingService.rateDoctor(testRating);

        // Assert
        assertNotNull(result);
        verify(ratingRepository, times(1)).save(any(DoctorRating.class));
    }

    @Test
    @DisplayName("Should calculate average rating correctly")
    void testAverageRatingCalculation() throws IOException {
        // Arrange
        DoctorRating existingRating = new DoctorRating();
        existingRating.setDoctorId("doc123");
        existingRating.setRating(3);
        existingRating.setNumberOfPeopleRated(2);
        existingRating.setTotalRating(6);

        when(ratingRepository.findByDoctorId("doc123")).thenReturn(existingRating);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(existingRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        DoctorRating result = ratingService.rateDoctor(testRating);

        // Assert
        assertNotNull(result);
        // New total = 6 + 4 = 10, new average = 10 / 3 = 3
        assertEquals(10, result.getTotalRating());
        assertEquals(3, result.getNumberOfPeopleRated());
    }

    @Test
    @DisplayName("Should produce message for new rating")
    void testMessageProduction() throws IOException {
        // Arrange
        when(ratingRepository.findByDoctorId("doc123")).thenReturn(null);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(testRating);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        // Act
        ratingService.rateDoctor(testRating);

        // Assert
        verify(messageService, times(1)).produceMessage(eq("DOCTOR_RATING"), eq("doc123"), any());
    }

    @Test
    @DisplayName("Should handle repository null response")
    void testRepositoryNullResponse() throws IOException {
        // Arrange
        when(ratingRepository.findByDoctorId("doc456")).thenReturn(null);
        when(ratingRepository.save(any(DoctorRating.class))).thenReturn(null);
        doNothing().when(messageService).produceMessage(anyString(), anyString(), anyString());

        DoctorRating newRating = new DoctorRating();
        newRating.setDoctorId("doc456");
        newRating.setRating(3);

        // Act
        DoctorRating result = ratingService.rateDoctor(newRating);

        // Assert
        assertNull(result);
    }
}
