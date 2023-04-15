package com.medical.app.repository;

import com.medical.app.model.entity.MedicalExaminationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalExaminationDetailRepository extends JpaRepository<MedicalExaminationDetails,Integer> {
    Optional<List<MedicalExaminationDetails>> findMedicalExaminationDetailsByMedicalExaminationId(Integer id);
    List<MedicalExaminationDetails> findMedicalExaminationDetailsByCreatedDateAndRoomId(Date date, Integer roomId);
}
