package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_services")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private MedicalDepartment medicalDepartment;
    @ManyToOne
    @JoinColumn(name = "category_service_id")
    private CategoryService categoryService;
}
