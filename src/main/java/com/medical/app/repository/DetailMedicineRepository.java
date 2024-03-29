package com.medical.app.repository;

import com.medical.app.model.entity.DetailMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailMedicineRepository extends JpaRepository<DetailMedicine, Integer> {

    List<DetailMedicine> findDetailMedicinesByMedicalExaminationId(Integer id);
    DetailMedicine findDetailMedicineByMedicalExaminationIdAndDrugId(Integer medicalId,Integer drugId );
}
