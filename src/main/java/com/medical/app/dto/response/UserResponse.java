package com.medical.app.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    private Integer id;
    private String phoneNumber;
    private String fullName;
    private Date dateOfBirth;
    private String idCardNumber;
    private String address;
    private Date createdDate;
    private Date updatedDate;
    private RoomResponse room;
    private String role;
    private String avatar;
}
