package com.medical.app.repository;

import com.medical.app.model.entity.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Repository
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {
    List<MedicalExamination> findMedicalExaminationsByPatientId(Integer id);
    List<MedicalExamination> findMedicalExaminationsByCreatedDateAndDoctorRoomId(Date date, Integer id);
}
