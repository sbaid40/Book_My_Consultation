package com.upgrad.appointmentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.appointmentservice.dto.AvailabilityData;
import com.upgrad.appointmentservice.entity.Availability;
import com.upgrad.appointmentservice.repository.AvailabilityRepository;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    //method to update Doctors availability
    public void updateDoctorsAvailability(String doctorId, AvailabilityData availabilityData) {
        Map<String, List<String>> map = availabilityData.getAvailabilityMap();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            Availability availability = Availability.builder().availabilityDate(LocalDate.parse(entry.getKey())).doctorId(doctorId).isBooked(false).timeSlot(entry.getValue()).build();
            this.availabilityRepository.save(availability);
        }
    }
    //method to get Doctors availibility data using doctor ID
    public AvailabilityData getDoctorsAvailablityData(String doctorId) {
        List<Availability> availability = this.availabilityRepository.findByDoctorId(doctorId);
        Map<String, List<String>> map = new HashMap<>();
        availability.stream().forEach(data->{
                map.put(data.getAvailabilityDate().toString(), data.getTimeSlot());
        });
        AvailabilityData availabilityData = AvailabilityData.builder().doctorId(doctorId).availabilityMap(map).build();
        return availabilityData;
    }
}
