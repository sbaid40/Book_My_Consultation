package com.upgrad.appointmentservice.repository;

import com.upgrad.appointmentservice.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    public List<Availability> findByDoctorId(String doctorId);
}
