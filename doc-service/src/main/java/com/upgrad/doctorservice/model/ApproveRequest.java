package com.upgrad.doctorservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveRequest {
    private String approvedBy;
    private String approverComments;
}
