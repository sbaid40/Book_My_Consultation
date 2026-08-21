package com.upgrad.appointmentservice.service;

import com.upgrad.appointmentservice.dto.AvailabilityData;
import com.upgrad.appointmentservice.entity.Availability;
import com.upgrad.appointmentservice.repository.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Availability Service Tests")
class AvailabilityServiceTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    private String doctorId = "doc123";
    private AvailabilityData availabilityData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<String, List<String>> map = new HashMap<>();
        map.put("2024-02-15", Arrays.asList("09:00", "10:00", "11:00"));
        map.put("2024-02-16", Arrays.asList("02:00", "03:00"));
        availabilityData = new AvailabilityData(doctorId,map);
    }

    @Test
    @DisplayName("Should update doctor's availability successfully")
    void testUpdateDoctorsAvailabilitySuccess() {
        // Arrange
        when(availabilityRepository.save(any(Availability.class)))
                .thenReturn(new Availability());

        // Act
        availabilityService.updateDoctorsAvailability(doctorId, availabilityData);

        // Assert
        verify(availabilityRepository, times(2)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Should save availability for each date")
    void testSaveAvailabilityForEachDate() {
        // Arrange
        when(availabilityRepository.save(any(Availability.class)))
                .thenReturn(new Availability());

        // Act
        availabilityService.updateDoctorsAvailability(doctorId, availabilityData);

        // Assert
        verify(availabilityRepository, atLeast(2)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Should get doctor's availability data successfully")
    void testGetDoctorsAvailabilityDataSuccess() {
        // Arrange
        Availability av1 = new Availability();
        av1.setDoctorId(doctorId);
        av1.setAvailabilityDate(LocalDate.of(2024, 2, 15));
        av1.setTimeSlot(Arrays.asList("09:00", "10:00"));
        av1.setIsBooked(false);

        Availability av2 = new Availability();
        av2.setDoctorId(doctorId);
        av2.setAvailabilityDate(LocalDate.of(2024, 2, 16));
        av2.setTimeSlot(Arrays.asList("02:00", "03:00"));
        av2.setIsBooked(false);

        when(availabilityRepository.findByDoctorId(doctorId))
                .thenReturn(Arrays.asList(av1, av2));

        // Act
        AvailabilityData result = availabilityService.getDoctorsAvailablityData(doctorId);

        // Assert
        assertNotNull(result);
        assertEquals(doctorId, result.getDoctorId());
        assertEquals(2, result.getAvailabilityMap().size());
        verify(availabilityRepository, times(1)).findByDoctorId(doctorId);
    }

    @Test
    @DisplayName("Should return empty map when no availability found")
    void testGetDoctorsAvailabilityDataEmpty() {
        // Arrange
        when(availabilityRepository.findByDoctorId(anyString()))
                .thenReturn(new ArrayList<>());

        // Act
        AvailabilityData result = availabilityService.getDoctorsAvailablityData("nonExistent");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getAvailabilityMap().size());
    }

    @Test
    @DisplayName("Should handle multiple time slots per date")
    void testMultipleSlotsPerDate() {
        // Arrange
        Availability availability = new Availability();
        availability.setDoctorId(doctorId);
        availability.setAvailabilityDate(LocalDate.of(2024, 2, 15));
        availability.setTimeSlot(Arrays.asList("09:00", "10:00", "11:00", "02:00"));
        availability.setIsBooked(false);

        when(availabilityRepository.findByDoctorId(doctorId))
                .thenReturn(Collections.singletonList(availability));

        // Act
        AvailabilityData result = availabilityService.getDoctorsAvailablityData(doctorId);

        // Assert
        assertEquals(4, result.getAvailabilityMap().get("2024-02-15").size());
    }

    @Test
    @DisplayName("Should not book availability by default")
    void testAvailabilityNotBookedByDefault() {
        // Arrange
        Availability av = new Availability();
        av.setIsBooked(false);

        when(availabilityRepository.save(any(Availability.class))).thenReturn(av);

        // Act
        availabilityService.updateDoctorsAvailability(doctorId, availabilityData);

        // Assert
        verify(availabilityRepository, times(2)).save(any(Availability.class));
    }

//    @Test
//    @DisplayName("Should handle single date availability")
//    void testSingleDateAvailability() {
//        // Arrange
//        AvailabilityData singleDateData = new AvailabilityData();
//        singleDateData.setDoctorId(doctorId);
//        Map<String, List<String>> singleMap = new HashMap<>();
//        singleMap.put("2024-02-15", Arrays.asList("09:00", "10:00"));
//        singleDateData.setAvailabilityMap(singleMap);
//
//        when(availabilityRepository.save(any(Availability.class)))
//                .thenReturn(new Availability());
//
//        // Act
//        availabilityService.updateDoctorsAvailability(doctorId, singleDateData);
//
//        // Assert
//        verify(availabilityRepository, times(1)).save(any(Availability.class));
//    }

    @Test
    @DisplayName("Should process availability in correct format")
    void testAvailabilityFormat() {
        // Arrange
        Availability av = new Availability();
        av.setDoctorId(doctorId);
        av.setAvailabilityDate(LocalDate.of(2024, 2, 15));
        av.setTimeSlot(Arrays.asList("09:00", "10:00"));

        when(availabilityRepository.findByDoctorId(doctorId))
                .thenReturn(Collections.singletonList(av));

        // Act
        AvailabilityData result = availabilityService.getDoctorsAvailablityData(doctorId);

        // Assert
        assertNotNull(result.getAvailabilityMap().get("2024-02-15"));
        assertEquals(2, result.getAvailabilityMap().get("2024-02-15").size());
    }

    @Test
    @DisplayName("Should preserve doctor ID in availability data")
    void testDoctorIdPreservation() {
        // Arrange
        when(availabilityRepository.findByDoctorId(doctorId))
                .thenReturn(new ArrayList<>());

        // Act
        AvailabilityData result = availabilityService.getDoctorsAvailablityData(doctorId);

        // Assert
        assertEquals(doctorId, result.getDoctorId());
    }
}
