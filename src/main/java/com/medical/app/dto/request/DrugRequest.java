package com.medical.app.dto.request;

import com.medical.app.model.entity.CategoryDrug;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Date createdDate;
    private Date updatedDate;
    private Integer unitId;
    private Integer vienVi;
    private Integer viHop;
    private Integer hopThung;
}
