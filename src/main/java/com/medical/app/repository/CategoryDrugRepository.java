package com.medical.app.repository;

import com.medical.app.model.entity.CategoryDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDrugRepository extends JpaRepository<CategoryDrug, Integer> {

}
