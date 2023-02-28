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
public class MedicalExaminationDetailsResponse {

    private Integer id;
    private Double unitPrice;
    private int quality;
    private Date createdDate;
    private Date updatedDate;
    private ServiceResponse serviceResponse;
    private RoomResponse room;
    private MedicalExaminationResponse medicalExamination;
}
