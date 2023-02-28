package com.medical.app.dto.response;

import com.medical.app.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BatchDrugResponse {

    private Integer id;
    private String name;
    private String description;
    private Date receiptDate;
    private Date createdDate;
    private Date updatedDate;
    private SupplierResponse supplierResponse;
    private UserResponse user;
    private List<DetailBatchDrugResponse> detailBatchDrugResponses;
}
