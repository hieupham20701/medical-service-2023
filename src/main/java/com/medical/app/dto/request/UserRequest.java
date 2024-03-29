package com.medical.app.dto.request;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    private String phoneNumber;
    private String fullName;
    private String password;
    private Date dateOfBirth;
    private String idCardNumber;
    private String address;
    private String roomId;
    private Date createdDate;
    private Date updatedDate;
    private String role;
    private String avatar;
    private String email;
    private Boolean sex;
}
