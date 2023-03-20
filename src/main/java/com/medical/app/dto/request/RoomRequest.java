package com.medical.app.dto.request;

import com.medical.app.model.entity.MedicalDepartment;

import java.util.Date;

public class RoomRequest {

    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Date createdDate;
    private Date updateDate;
    private MedicalDepartmentRequest medicalDepartment;
}
