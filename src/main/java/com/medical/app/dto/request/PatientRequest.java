package com.medical.app.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientRequest {

    private String idCard;
    private String phoneNumber;
    private String fullName;
    private String address;
    private Date createdDate;
    private Date updateDate;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private String insuranceNumber;
    private Boolean sex;
}
