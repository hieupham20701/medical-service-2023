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
public class MedicineResponse {
    private Integer id;
    private Double totalPrice;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private MedicalExaminationResponse medicalExaminationResponse;
    private List<DetailMedicineResponse> detailMedicineResponses;
}
