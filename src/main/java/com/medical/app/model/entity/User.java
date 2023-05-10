package com.medical.app.model.entity;


import com.medical.app.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phoneNumber;
    private String fullName;
    private String password;
    private Date dateOfBirth;
    private String idCardNumber;
    private String address;
    private Date createdDate;
    private Date updatedDate;
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String email;
    private Boolean sex;
}
