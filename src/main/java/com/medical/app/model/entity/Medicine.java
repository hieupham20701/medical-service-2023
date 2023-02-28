package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tbl_medicine")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalPrice;
    private String description;
    private Date createdDate;
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "medical_examination_id")
    private MedicalExamination medicalExamination;
}
