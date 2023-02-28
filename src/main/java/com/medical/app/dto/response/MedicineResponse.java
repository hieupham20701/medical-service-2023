package com.medical.app.dto.response;

import com.medical.app.model.entity.MedicalExamination;
import com.medical.app.model.entity.Medicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
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
