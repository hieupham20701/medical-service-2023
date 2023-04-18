package com.medical.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailServiceResponse {
    private Integer id;
    private UserResponse doctor;
    private PatientResponse patient;
    private List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses;
    private Date createdDate;
    private Date updatedDate;

}
