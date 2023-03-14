package com.medical.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointmentLetterResponse {

    private Integer id;
    private Date date;
    private UserResponse doctor;
    private Date createdDate;
    private Date updatedDate;
    private ServiceResponse service;
    private PatientResponse patient;
    private UserResponse creator;
    private String status;
    private String description;
}
