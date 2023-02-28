package com.medical.app.dto.request;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalExaminationRequest {
    private Integer id;
    private String diagnose;
    private Date date_recheck_up;
    private Double total_price;
    private Boolean buy_medicine;
    private Date created_date;
    private Date updated_date;
    private PatientRequest patient;
    private String status;
    private List<MedicalExaminationDetailsRequest> medicalExaminationDetailsRequests;
    private Integer doctor_id;
    private Integer reception_id;
}
