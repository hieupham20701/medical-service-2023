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
public class MedicalExaminationDetailsRequest {

    private Double unit_price;
    private int quality;
    private Date created_date;
    private Date updated_date;
    private Integer service_id;
    private Integer room_id;
    private Integer medicalExamination_id;
}
