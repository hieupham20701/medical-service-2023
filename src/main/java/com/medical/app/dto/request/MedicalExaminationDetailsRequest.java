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

    private Double unitPrice;
    private int quality;
    private Date createdDate;
    private Date updatedDate;
    private Integer serviceId;
    private Integer roomId;
    private Integer medicalExaminationId;
}
