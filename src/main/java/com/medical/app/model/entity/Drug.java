package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_drugs")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String unit;
    private String benefit;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_drug_id")
    private CategoryDrug categoryDrug;
    private String note;
    private String image;
    private Date createdDate;
    private Date updatedDate;
    private Integer unitId;
    private Integer vienVi;
    private Integer viHop;
    private Integer hopThung;
}
