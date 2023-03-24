package com.medical.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServiceRequest {

    private Integer id;
    private String name;
    private Double price;
    private Date created_date;
    private Date updated_date;
    private Integer medicalDepartmentId;
    private Integer categoryServiceId;
}
