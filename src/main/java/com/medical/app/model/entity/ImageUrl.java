package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_image_url")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "medical_detail_id")
    private MedicalExaminationDetails medicalExaminationDetails;
    private Date createdDate;
    private Date updatedDate;

}
