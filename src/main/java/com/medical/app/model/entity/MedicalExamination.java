package com.medical.app.model.entity;

import com.medical.app.model.enums.StatusMedicalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_medical_examinations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diagnose;
    private Date dateRecheckUp;
    private Double totalPrice;
    private Boolean buyMedicine;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Enumerated(EnumType.STRING)
    private StatusMedicalDetail status;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;
    @ManyToOne
    @JoinColumn(name = "reception_id")
    private User reception;
    private String description;
    private String note;
    private Double weight;
    private Double height;
    private Double heartbeat;
    private String bloodPressure;
    private Double temperature;
    private Double para;
    private String result;
    private String pathological;
    private String codeicd;
}
