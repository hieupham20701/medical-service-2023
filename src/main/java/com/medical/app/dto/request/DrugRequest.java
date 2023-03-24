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
public class DrugRequest {

    private String name;
    private String description;
    private String unit;
    private String benefit;
    private Double price;
    private Integer categoryDrugId;
    private String note;
    private String image;
}
