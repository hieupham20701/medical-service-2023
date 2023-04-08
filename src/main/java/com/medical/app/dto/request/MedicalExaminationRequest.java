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
    private Date dateRecheckUp;
    private Double totalPrice;
    private Boolean buyMedicine;
    private Date createdDate;
    private Date updatedDate;
    private PatientRequest patient;
    private String status;
    private List<MedicalExaminationDetailsRequest> medicalExaminationDetailsRequests;
    private List<DetailMedicineRequest> detailMedicineRequests;
    private Integer doctorId;
    private Integer receptionId;
    private String description;
    private String note;
    private Double weight;
    private Double height;
    private Double heartbeat;
    private String bloodPressure;
    private Double temperature;
    private Double para;
    private String result;
    private String pathological;
    private String codeicd;
}
