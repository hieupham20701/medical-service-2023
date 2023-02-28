package com.medical.app.repository;

import com.medical.app.model.entity.DetailBatchDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailBatchDrugRepository extends JpaRepository<DetailBatchDrug, Integer> {

    Optional<List<DetailBatchDrug>> findDetailBatchDrugsByBatchDrugId(Integer batchDrugId);
    List<DetailBatchDrug> findDetailBatchDrugsByDrugId(Integer id);
}
