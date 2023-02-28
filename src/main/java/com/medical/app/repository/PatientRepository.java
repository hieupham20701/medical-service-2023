package com.medical.app.repository;

import com.medical.app.dto.response.PatientResponse;
import com.medical.app.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> getPatientByPhoneNumber(String phoneNumber);
}
