package com.upgrad.appointmentservice.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Appointment")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name="appointment_date")
    private LocalDate appointmentDate;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="doctor_id" , length =255 )
    private String doctorId;

    @Column(name="prior_medical_history" , length =255 )
    private String priorMedicalHistory;

    @Column(name="status" , length =255 )
    private String status;

    @Column(name="symptoms" , length =255 )
    private String symptoms;

    @Column(name="time_slot" , length =255 )
    private String timeSlot;

    @Column(name="user_id" , length =255 )
    private String userId;

    @Column(name="user_email_id" , length =255 )
    private String userEmailId;

    @Column(name="user_name" , length =255 )
    private String userName;

    @Column(name="doctor_name" , length =255 )
    private String doctorName;

}
