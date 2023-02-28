package com.medical.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_batch_drug")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailBatchDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quality;
    private String unit;
    private Double price;
    private Date manufactureDate;
    private Date expiredDate;
    @ManyToOne
    @JoinColumn(name = "id_batch_drug")
    private BatchDrug batchDrug;

    @ManyToOne
    @JoinColumn(name = "id_drug")
    private Drug drug;
}
