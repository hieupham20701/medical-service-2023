package com.medical.app.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalExaminationResponse {
    private Integer id;
    private String diagnose;
    private Date dateRecheckUp;
    private Double totalPrice;
    private Boolean buyMedicine;
    private Date createdDate;
    private Date updatedDate;
    private PatientResponse patient;
    private String status;
    private UserResponse doctor;
    private UserResponse reception;
    private List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses;
}
