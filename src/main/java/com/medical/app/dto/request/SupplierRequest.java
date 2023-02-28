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
public class SupplierRequest {
    private String name;
    private String description;
    private String address;
    private String phone_number;
    private Date create_date;
    private Date updated_date;
}
