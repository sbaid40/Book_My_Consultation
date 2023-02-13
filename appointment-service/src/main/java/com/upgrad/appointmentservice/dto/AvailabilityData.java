package com.upgrad.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AvailabilityData {
    private String doctorId;
    private Map<String, List<String>> availabilityMap = new HashMap<>();
}

