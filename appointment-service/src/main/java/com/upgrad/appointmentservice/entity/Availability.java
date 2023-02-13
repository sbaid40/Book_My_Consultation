package com.upgrad.appointmentservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Availability")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="availability_date")
    private LocalDate availabilityDate;

    @Column(name="doctor_id" , length =255 )
    private String doctorId;

    @Column(name="is_booked" , nullable =  false)
    private Boolean isBooked;

    @ElementCollection
    @Column(name="time_slot" , length =255 )
    private List<String> timeSlot;

}
