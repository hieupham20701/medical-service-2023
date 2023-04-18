package com.medical.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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
