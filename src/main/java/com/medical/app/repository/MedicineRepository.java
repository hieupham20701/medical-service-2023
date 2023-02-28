package com.medical.app.repository;

import com.medical.app.model.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findMedicinesByMedicalExaminationPatientId(Integer patient_id);
}
