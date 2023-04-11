package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_batch_drug")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Date receiptDate;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private Double totalPrice;
    private Double settlement;
    private Double paidPrice;
    private Double debt;
}
