package com.medical.app.dto.response;

import com.google.firebase.database.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExaminationTable {

    private Integer id;
    private String fullName;
    private String status;
    private String diagnose;
    private String result;
    private Date old;
}
