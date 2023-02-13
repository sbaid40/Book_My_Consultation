package com.upgrad.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;




@Getter
@Setter
@Document(collection="Doctor")
public class Doctor {
    @Id
    private String id = this.getRandomStringForId();
    @NotBlank(message = "Student Name cannot be blank")
    @Size(min = 5, max = 20, message = "Student Name length should be between 5 and 20 characters")
    private String firstName;
    @NotBlank(message = "Student Name cannot be blank")
    @Size(min = 5, max = 20, message = "Student Name length should be between 5 and 20 characters")
    private String lastName;
    private String speciality = "GENERAL_PHYSICIAN";
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of Birth should be in yyyy-mm-dd format")
    private String dob;
    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number is invalid")
    private String mobile;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    private String emailId;
    @Pattern(regexp = "^[\\p{Alnum}]{1,32}$", message = "PAN Number should be AlphaNumeric")
    private String pan;
    private Status status= Status.PENDING;
    private String approvedBy;
    private String approverComments;
    private LocalDate registrationDate= LocalDate.now();
    private LocalDate verificationDate;
    private String rating = "Not Rated Yet";
    private String getRandomStringForId(){
        return RandomStringUtils.random(15,true,true);

    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", dob='" + dob + '\'' +
                ", mobile='" + mobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", pan='" + pan + '\'' +
                ", status=" + status +
                ", approvedBy='" + approvedBy + '\'' +
                ", approverComments='" + approverComments + '\'' +
                ", registrationDate=" + registrationDate +
                ", verificationDate=" + verificationDate +
                '}';
    }
}
