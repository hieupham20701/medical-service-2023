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
public class MedicalDepartmentResponse {
    private Integer id;
    private String name;
    private String description;
    private Date createdDate;
    private Date updatedDate;
}
