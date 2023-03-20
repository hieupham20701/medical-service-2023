package com.medical.app.dto.request;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDepartmentRequest {
    private String name;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private String codeDepartment;
}
