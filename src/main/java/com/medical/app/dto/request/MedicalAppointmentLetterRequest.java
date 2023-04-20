package com.medical.app.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalAppointmentLetterRequest {
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private Integer doctor_id;
    private Date created_date;
    private Date updated_date;
    private Integer service_id;
    private Integer creator_id;
    private String status;
    private String description;
    private String patientName;
    private String phoneNumber;
    private String address;
    private Boolean sex;
}
