package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.Status;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Doctor Data Service Tests")
class DoctorDataServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorDataService doctorDataService;

    private Doctor testDoctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testDoctor = new Doctor();
        testDoctor.setId("doc123");
        testDoctor.setFirstName("Dr. John");
        testDoctor.setLastName("Smith");
        testDoctor.setEmailId("john@hospital.com");
        testDoctor.setMobile("9876543210");
        testDoctor.setSpeciality("CARDIOLOGY");
        testDoctor.setStatus(Status.ACTIVE);
    }

    @Test
    @DisplayName("Should get all doctors data")
    void testGetAllDoctorsData() {
        // Arrange
        List<Doctor> doctors = Arrays.asList(testDoctor);
        when(doctorRepository.findAll()).thenReturn(doctors);

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsData();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("doc123", result.get(0).getId());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get doctors by speciality and status")
    void testGetAllDoctorsBasedOnSpecialityAndStatus() {
        // Arrange
        List<Doctor> doctors = Arrays.asList(testDoctor);
        when(doctorRepository.findBySpecialityAndStatus("CARDIOLOGY", Status.ACTIVE))
                .thenReturn(doctors);

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsBasedOnSpecialityAndStatus("CARDIOLOGY", Status.ACTIVE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(doctorRepository, times(1)).findBySpecialityAndStatus("CARDIOLOGY", Status.ACTIVE);
    }

    @Test
    @DisplayName("Should get doctors by status")
    void testGetAllDoctorsBasedOnStatus() {
        // Arrange
        List<Doctor> doctors = Arrays.asList(testDoctor);
        when(doctorRepository.findByStatus(Status.ACTIVE))
                .thenReturn(doctors);

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsBasedOnStatus(Status.ACTIVE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(doctorRepository, times(1)).findByStatus(Status.ACTIVE);
    }

    @Test
    @DisplayName("Should get doctors by speciality")
    void testGetAllDoctorsBasedOnSpeciality() {
        // Arrange
        List<Doctor> doctors = Arrays.asList(testDoctor);
        when(doctorRepository.findBySpeciality("CARDIOLOGY"))
                .thenReturn(doctors);

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsBasedOnSpeciality("CARDIOLOGY");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(doctorRepository, times(1)).findBySpeciality("CARDIOLOGY");
    }

    @Test
    @DisplayName("Should get doctor by ID successfully")
    void testGetDoctorDataByIdSuccess() {
        // Arrange
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(testDoctor));

        // Act
        Doctor result = doctorDataService.getDoctorDataById("doc123");

        // Assert
        assertNotNull(result);
        assertEquals("doc123", result.getId());
        assertEquals("Dr. John", result.getFirstName());
        verify(doctorRepository, times(1)).findById("doc123");
    }

    @Test
    @DisplayName("Should throw exception when doctor not found")
    void testGetDoctorDataByIdNotFound() {
        // Arrange
        when(doctorRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(com.upgrad.doctorservice.exception.RequestedResourceNotFoundException.class,
                     () -> doctorDataService.getDoctorDataById("nonExistent"));
        verify(doctorRepository, times(1)).findById("nonExistent");
    }

    @Test
    @DisplayName("Should return empty list when no doctors found")
    void testGetAllDoctorsEmptyList() {
        // Arrange
        when(doctorRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsData();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get multiple doctors by speciality")
    void testGetMultipleDoctorsBySpeciality() {
        // Arrange
        Doctor doctor2 = new Doctor();
        doctor2.setId("doc456");
        doctor2.setFirstName("Dr. Jane");
        doctor2.setSpeciality("CARDIOLOGY");

        List<Doctor> doctors = Arrays.asList(testDoctor, doctor2);
        when(doctorRepository.findBySpeciality("CARDIOLOGY")).thenReturn(doctors);

        // Act
        List<Doctor> result = doctorDataService.getAllDoctorsBasedOnSpeciality("CARDIOLOGY");

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should verify repository call count")
    void testRepositoryCallCount() {
        // Arrange
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(testDoctor));

        // Act
        doctorDataService.getDoctorDataById("doc123");

        // Assert
        verify(doctorRepository, times(1)).findById("doc123");
        verifyNoMoreInteractions(doctorRepository);
    }
}
