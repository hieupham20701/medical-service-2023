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
public class ServiceResponse {

    private Integer id;
    private String name;
    private Double price;
    private Date createdDate;
    private Date updatedDate;
    private MedicalDepartmentResponse medicalDepartment;
    private CategoryServiceResponse categoryService;
}
