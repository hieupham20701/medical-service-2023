package com.medical.app.dto.request;

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

    private Date date;
    private Integer doctor_id;
    private Date created_date;
    private Date updated_date;
    private Integer service_id;
    private String patient_name;
    private String address;
    private String phone_number;
    private Integer creator_id;
    private String status;
    private String description;
}
