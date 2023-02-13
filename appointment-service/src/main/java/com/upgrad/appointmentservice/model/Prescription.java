package com.upgrad.appointmentservice.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection="Prescription")
public class Prescription {

    private String id = this.getRandomStringForId();
    private String userId;
    private String doctorId;
    private String doctorName;
    private String appointmentId;
    private String diagnosis;
    List<Medicine> medicineList;

    private String getRandomStringForId(){
        return RandomStringUtils.random(15,true,true);

    }

}
