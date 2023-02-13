package com.upgrad.appointmentservice.repository;

import com.upgrad.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    public List<Appointment> findAppointmentByUserId(String userId);
    public Appointment findByAppointmentId(String appointmentId);
}
