package com.medical.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailBatchDrugRequest {

    private Integer quality;
    private String unit;
    private Double price;
    private Date manufacture_date;
    private Date expired_date;
    private Integer batch_drug_id;
    private Integer drug_id;
}
