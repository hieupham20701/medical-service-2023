package com.medical.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailMedicineResponse {
    private Integer id;
    private Integer quality;
    private String amount;
    private Double totalPrice;
    private DrugResponse drug;
    private String unit;
    private Date createdDate;
    private Date updatedDate;

}
