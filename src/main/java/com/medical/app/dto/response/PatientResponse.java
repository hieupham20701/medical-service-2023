package com.medical.app.dto.response;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PatientResponse {
    private Integer id;
    private String idCard;
    private String phoneNumber;
    private String fullName;
    private String address;
    private Date createdDate;
    private Date updateDate;
    private Date dateOfBirth;
    private String insuranceNumber;
    private Boolean sex;
}
