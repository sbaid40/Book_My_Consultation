package com.upgrad.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {
    private String errorCode;
    private String errorMessage;
    private List<String> errorFieldsList;
}

