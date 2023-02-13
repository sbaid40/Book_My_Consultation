package com.upgrad.notificationservice.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Prescription {

    private String id;
    private String userId;
    private String doctorId;
    private String doctorName;
    private String appointmentId;
    private String diagnosis;
    List<Medicine> medicineList;
}
