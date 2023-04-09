package com.medical.app.dto.request;

import com.medical.app.model.entity.MedicalExaminationDetails;
import java.util.Date;

public class ImageUrlRequest {
    private Integer id;
    private String url;
    private Integer medicalDetail_id;
    private Date createdDate;
    private Date updatedDate;
}
