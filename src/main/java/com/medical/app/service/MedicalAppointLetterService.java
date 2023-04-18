package com.medical.app.service;

import com.medical.app.dto.request.MedicalAppointmentLetterRequest;
import com.medical.app.dto.response.MedicalAppointmentLetterResponse;

import java.util.Date;
import java.util.List;

public interface MedicalAppointLetterService {

    MedicalAppointmentLetterResponse saveMedicalAppointmentLetter(MedicalAppointmentLetterRequest medicalAppointmentLetterRequest);
    MedicalAppointmentLetterResponse getLetterByPatientPhoneNumber(String phoneNumber);
    MedicalAppointmentLetterResponse getLetterById(Integer id);
    List<MedicalAppointmentLetterResponse> getAllLetter();

    List<MedicalAppointmentLetterResponse> findLettersByDateBetween(Date from, Date to);

    MedicalAppointmentLetterResponse updateMedicalAppointmentLetter(Integer id, MedicalAppointmentLetterRequest medicalAppointmentLetterRequest);

    List<MedicalAppointmentLetterResponse> findLetterByParam(String patientName, String phoneNumber);
    List<MedicalAppointmentLetterResponse> findLetterByDate(Date date);
}
