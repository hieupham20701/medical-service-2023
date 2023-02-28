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
public class RoomResponse {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Date createdDate;
    private Date updateDate;
    private MedicalDepartmentResponse medicalDepartment;
}
