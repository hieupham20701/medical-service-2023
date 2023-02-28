package com.medical.app.repository;

import com.medical.app.model.entity.BatchDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDrugRepository extends JpaRepository<BatchDrug, Integer> {
}
