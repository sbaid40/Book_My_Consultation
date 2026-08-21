package com.upgrad.appointmentservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Availability Data DTO Tests")
class AvailabilityDataTest {

    private AvailabilityData availabilityData;

    @BeforeEach
    void setUp() {
        availabilityData = AvailabilityData.builder()
                .doctorId("")
                .availabilityMap(new HashMap<>())
                .build();
    }

    @Test
    @DisplayName("Should set and get doctorId")
    void testSetGetDoctorId() {
        availabilityData.setDoctorId("doc123");
        assertEquals("doc123", availabilityData.getDoctorId());
    }

    @Test
    @DisplayName("Should set and get availabilityMap")
    void testSetGetAvailabilityMap() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> slots = new ArrayList<>();
        slots.add("10:00");
        slots.add("11:00");
        map.put("2024-02-15", slots);

        availabilityData.setAvailabilityMap(map);
        assertEquals(map, availabilityData.getAvailabilityMap());
    }

    @Test
    @DisplayName("Should populate all fields")
    void testPopulateAllFields() {
        availabilityData.setDoctorId("doc456");
        Map<String, List<String>> map = new HashMap<>();
        map.put("2024-03-20", List.of("02:00", "03:00"));
        availabilityData.setAvailabilityMap(map);

        assertEquals("doc456", availabilityData.getDoctorId());
        assertEquals(1, availabilityData.getAvailabilityMap().size());
    }

    @Test
    @DisplayName("Should handle empty availability map")
    void testEmptyAvailabilityMap() {
        availabilityData.setAvailabilityMap(new HashMap<>());
        assertNotNull(availabilityData.getAvailabilityMap());
        assertEquals(0, availabilityData.getAvailabilityMap().size());
    }

    @Test
    @DisplayName("Should handle multiple time slots")
    void testMultipleTimeSlots() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("2024-02-15", List.of("09:00", "10:00", "11:00", "02:00"));
        availabilityData.setAvailabilityMap(map);

        assertEquals(4, availabilityData.getAvailabilityMap().get("2024-02-15").size());
    }

    @Test
    @DisplayName("Should handle multiple dates")
    void testMultipleDates() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("2024-02-15", List.of("09:00", "10:00"));
        map.put("2024-02-16", List.of("02:00", "03:00"));
        map.put("2024-02-17", List.of("04:00", "05:00"));
        availabilityData.setAvailabilityMap(map);

        assertEquals(3, availabilityData.getAvailabilityMap().size());
    }

    @Test
    @DisplayName("Should handle null values")
    void testNullValues() {
        availabilityData.setDoctorId(null);
        availabilityData.setAvailabilityMap(null);
        assertNull(availabilityData.getDoctorId());
        assertNull(availabilityData.getAvailabilityMap());
    }

    @Test
    @DisplayName("Should modify fields after creation")
    void testModifyFields() {
        availabilityData.setDoctorId("doc1");
        assertEquals("doc1", availabilityData.getDoctorId());

        availabilityData.setDoctorId("doc2");
        assertEquals("doc2", availabilityData.getDoctorId());
    }
}
