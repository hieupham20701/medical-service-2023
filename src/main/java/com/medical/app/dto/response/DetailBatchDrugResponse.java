package com.medical.app.dto.response;

import com.medical.app.model.entity.Drug;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailBatchDrugResponse {
    private Integer id;
    private Integer quality;
    private String unit;
    private Double price;
    private Drug drug;
}
