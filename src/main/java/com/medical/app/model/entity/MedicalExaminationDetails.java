package com.medical.app.model.entity;

import com.medical.app.model.enums.StatusMedicalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_medical_examinations_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalExaminationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double unitPrice;
    private int quality;

    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "medical_exam_id")
    private MedicalExamination medicalExamination;
}
