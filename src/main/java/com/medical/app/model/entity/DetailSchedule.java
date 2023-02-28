package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_schedule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date createdDate;
    private Date updateDate;
    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
