package com.medical.app.repository;

import com.medical.app.model.entity.MedicalExamination;
import com.medical.app.model.enums.StatusMedicalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Repository
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {
    List<MedicalExamination> findMedicalExaminationsByPatientId(Integer id);
    List<MedicalExamination> findMedicalExaminationsByCreatedDateAndDoctorRoomIdAndDoctorId(Date date, Integer id, Integer doctorId);
    List<MedicalExamination> findMedicalExaminationsByCreatedDate(Date date);
    List<MedicalExamination> findMedicalExaminationsByPatientFullNameContaining(String keyword);
    List<MedicalExamination> findMedicalExaminationsByPatientPhoneNumber(String keyword);
    List<MedicalExamination> findMedicalExaminationsByDoctorRoomMedicalDepartmentIdAndCreatedDateAndStatus(Integer departmentId, Date createdDate, StatusMedicalDetail status);
}
