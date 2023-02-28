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

    private Double total_price;
    private String description;
    private Date created_date;
    private Date updated_date;
    private Integer medical_examination_id;
    private List<DetailMedicineRequest> detail_medicines;
}
