package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_category_drug")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date createdDate;
    private Date updatedDate;

}
