package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_patient")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idCard;
    private String phoneNumber;
    private String fullName;
    private String address;
    private Date createdDate;
    private Date updatedDate;
    private Date dateOfBirth;
    private String insuranceNumber;
    private Boolean sex;
}
