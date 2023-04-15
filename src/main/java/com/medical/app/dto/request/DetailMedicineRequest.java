package com.medical.app.dto.request;

import com.medical.app.model.entity.Drug;
import com.medical.app.model.entity.MedicalExaminationDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailMedicineRequest {

    private Integer quality;
    private String designate;
    private Double totalPrice;
    private Integer drugId;
    private Integer medicalExaminationId;
    private Date createdDate;
    private Date updatedDate;

}
