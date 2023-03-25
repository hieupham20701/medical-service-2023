package com.medical.app.dto.request;

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
public class MedicineRequest {

    private Double totalPrice;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private Integer medicalExamination_id;
    private List<DetailMedicineRequest> detail_medicines;
}
