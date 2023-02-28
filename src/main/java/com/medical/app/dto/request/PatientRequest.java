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
public class PatientRequest {

    private String id_card;
    private String phone_number;
    private String full_name;
    private String address;
    private Date created_date;
    private Date update_date;
    private Date date_of_birth;
    private String insurance_number;
    private Boolean sex;
}
