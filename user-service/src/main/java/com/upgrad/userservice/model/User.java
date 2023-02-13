package com.upgrad.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Document(collection="User")
public class User {
    private String id  = this.getRandomStringForId();
    @NotBlank(message = "Student Name cannot be blank")
    @Size(min = 3, max = 20, message = "Student Name length should be between 5 and 20 characters")
    private String firstName;
    @NotBlank(message = "Student Name cannot be blank")
    @Size(min = 3, max = 20, message = "Student Name length should be between 5 and 20 characters")
    private String lastName;
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date of Birth should be in yyyy-mm-dd format")
    private String dob;
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number is invalid")
    private String mobile;
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    private String emailId;
    private LocalDate createdDate = LocalDate.now();
    @JsonIgnore
    private boolean hasRatedDoctor = false;

    private String getRandomStringForId(){
        return RandomStringUtils.random(15,true,true);

    }
}
