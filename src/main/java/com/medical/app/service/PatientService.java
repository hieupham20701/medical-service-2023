package com.medical.app.service;

import com.medical.app.dto.request.PatientRequest;
import com.medical.app.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {

    PatientResponse getPatientByPhoneNumber(String phoneNumber);

    PatientResponse savePatientInfo(PatientRequest request);
    List<PatientResponse> getAllPatient();
    PatientResponse getPatientById(Integer id);


}
