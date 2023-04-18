package com.medical.app.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medical.app.model.enums.StatusLetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_medical_appointment_letter")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointmentLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "doctor_in_charge")
    private User doctor;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusLetter status;
}
