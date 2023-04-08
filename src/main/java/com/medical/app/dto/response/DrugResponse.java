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
public class DrugResponse {
    private Integer id;
    private String name;
    private String description;
    private String unit;
    private String benefit;
    private Double price;
    private CategoryDrugResponse categoryDrug;
    private Date manufactureDate;
    private Date expiredDate;
    private Date createdDate;
    private Date updatedDate;
    private Integer quality;
    private String note;
    private String image;
    private Integer vienVi;
    private Integer viHop;
    private Integer hopThung;
}
