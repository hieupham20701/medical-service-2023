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
public class CategoryServiceResponse {
    private Integer id;
    private String name;
    private Date createdDate;
    private Date updateDate;
}
