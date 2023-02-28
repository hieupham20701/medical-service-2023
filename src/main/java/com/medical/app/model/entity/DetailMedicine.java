package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tbl_detail_medicine")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quality;
    private String amount;
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    private String unit;
    private Date createdDate;
    private Date updatedDate;
    private String description;
}
