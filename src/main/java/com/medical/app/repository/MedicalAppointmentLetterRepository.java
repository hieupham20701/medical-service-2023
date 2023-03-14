package com.medical.app.repository;

import com.medical.app.model.entity.MedicalAppointmentLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalAppointmentLetterRepository extends JpaRepository<MedicalAppointmentLetter, Integer> {

    Optional<MedicalAppointmentLetter> findMedicalAppointmentLetterByPatientPhoneNumber(String phoneNumber);
    List<MedicalAppointmentLetter> findMedicalAppointmentLettersByDateBetween(Date from, Date to);

    List<MedicalAppointmentLetter> findMedicalAppointmentLetterByPatientFullNameOrPatientPhoneNumber(String patientName, String phoneNumber);
}
