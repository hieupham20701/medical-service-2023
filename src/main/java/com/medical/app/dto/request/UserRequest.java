package com.medical.app.dto.request;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    private String phone_number;
    private String full_name;
    private String password;
    private Date date_of_birth;
    private String id_card_number;
    private String address;
    private Date created_date;
    private Date updated_date;
    private String role;
}
