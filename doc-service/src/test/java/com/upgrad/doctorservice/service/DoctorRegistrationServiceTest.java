package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Doctor Registration Service Tests")
class DoctorRegistrationServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorRegistrationService doctorRegistrationService;

    private Doctor testDoctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testDoctor = new Doctor();
        testDoctor.setFirstName("Dr. Test");
        testDoctor.setLastName("Doctor");
        testDoctor.setEmailId("test@hospital.com");
        testDoctor.setMobile("9876543210");
        testDoctor.setSpeciality("GENERAL");
    }

    @Test
    @DisplayName("Should save doctor registration data successfully")
    void testSaveDoctorRegistrationDataSuccess() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setId("doc123");
        savedDoctor.setFirstName("Dr. Test");
        savedDoctor.setLastName("Doctor");

        when(doctorRepository.insert(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        Doctor result = doctorRegistrationService.saveDoctorRegistrationData(testDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("doc123", result.getId());
        assertEquals("Dr. Test", result.getFirstName());
        verify(doctorRepository, times(1)).insert(testDoctor);
    }

    @Test
    @DisplayName("Should handle null return from repository")
    void testSaveDoctorRegistrationDataNull() {
        // Arrange
        when(doctorRepository.insert(any(Doctor.class))).thenReturn(null);

        // Act
        Doctor result = doctorRegistrationService.saveDoctorRegistrationData(testDoctor);

        // Assert
        assertNull(result);
        verify(doctorRepository, times(1)).insert(testDoctor);
    }

    @Test
    @DisplayName("Should call repository insert exactly once")
    void testRepositoryCallCount() {
        // Arrange
        when(doctorRepository.insert(any(Doctor.class))).thenReturn(testDoctor);

        // Act
        doctorRegistrationService.saveDoctorRegistrationData(testDoctor);

        // Assert
        verify(doctorRepository, times(1)).insert(any(Doctor.class));
        verifyNoMoreInteractions(doctorRepository);
    }

    @Test
    @DisplayName("Should save doctor with all details")
    void testSaveDoctorWithAllDetails() {
        // Arrange
        Doctor completeDoctor = new Doctor();
        completeDoctor.setFirstName("Dr. Complete");
        completeDoctor.setLastName("Profile");
        completeDoctor.setEmailId("complete@hospital.com");
        completeDoctor.setMobile("9123456789");
        completeDoctor.setSpeciality("NEUROLOGY");
        completeDoctor.setPan("ABCDE1234F");

        Doctor savedDoctor = new Doctor();
        savedDoctor.setId("docComplete");
        savedDoctor.setFirstName("Dr. Complete");

        when(doctorRepository.insert(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        Doctor result = doctorRegistrationService.saveDoctorRegistrationData(completeDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("docComplete", result.getId());
        verify(doctorRepository, times(1)).insert(completeDoctor);
    }

    @Test
    @DisplayName("Should preserve doctor data during save")
    void testPreserveDoctorData() {
        // Arrange
        Doctor savedDoctor = new Doctor();
        savedDoctor.setId("docPreserved");
        savedDoctor.setFirstName("Dr. Test");
        savedDoctor.setLastName("Doctor");
        savedDoctor.setEmailId("test@hospital.com");
        savedDoctor.setMobile("9876543210");

        when(doctorRepository.insert(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        Doctor result = doctorRegistrationService.saveDoctorRegistrationData(testDoctor);

        // Assert
        assertEquals("test@hospital.com", result.getEmailId());
        assertEquals("9876543210", result.getMobile());
    }

    @Test
    @DisplayName("Should handle exception from repository")
    void testRepositoryException() {
        // Arrange
        when(doctorRepository.insert(any(Doctor.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class,
                     () -> doctorRegistrationService.saveDoctorRegistrationData(testDoctor));
        verify(doctorRepository, times(1)).insert(any(Doctor.class));
    }

    @Test
    @DisplayName("Should save multiple doctors")
    void testSaveMultipleDoctors() {
        // Arrange
        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("Dr. One");
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Dr. Two");

        Doctor saved1 = new Doctor();
        saved1.setId("doc1");
        Doctor saved2 = new Doctor();
        saved2.setId("doc2");

        when(doctorRepository.insert(doctor1)).thenReturn(saved1);
        when(doctorRepository.insert(doctor2)).thenReturn(saved2);

        // Act
        Doctor result1 = doctorRegistrationService.saveDoctorRegistrationData(doctor1);
        Doctor result2 = doctorRegistrationService.saveDoctorRegistrationData(doctor2);

        // Assert
        assertEquals("doc1", result1.getId());
        assertEquals("doc2", result2.getId());
        verify(doctorRepository, times(2)).insert(any(Doctor.class));
    }
}
