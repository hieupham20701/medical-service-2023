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
    private Date receiptDate;
    private Date createdDate;
    private Date updatedDate;
    private Integer userId;
    private Integer supplierId;
    private List<DetailBatchDrugRequest> detailBatchDrug;
    private Double totalPrice;
    private Double settlement;
    private Double paidPrice;
    private Double debt;
}
