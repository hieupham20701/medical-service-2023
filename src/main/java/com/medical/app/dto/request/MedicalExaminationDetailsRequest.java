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
public class MedicalExaminationDetailsRequest {

    private Date createdDate;
    private Date updatedDate;
    private Integer serviceId;
    private Integer roomId;
    private Integer medicalExaminationId;
    private Integer id;
    private String type;
    private String status;
    private String image;
    private String result;
    private Boolean paid;
}
