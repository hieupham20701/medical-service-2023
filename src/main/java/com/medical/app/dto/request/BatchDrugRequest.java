package com.medical.app.dto.request;

import com.medical.app.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BatchDrugRequest {

    private String name;
    private String description;
    private Date receipt_date;
    private Date created_date;
    private Date updated_date;
    private Integer user_id;
    private Integer supplier_id;
    private List<DetailBatchDrugRequest> detail_batch_drug;
}
