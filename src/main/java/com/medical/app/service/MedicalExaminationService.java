package com.medical.app.service;

import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.MedicalExaminationResponse;

import java.util.List;

public interface MedicalExaminationService {

    MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest);
    MedicalExaminationResponse getMedicalExaminationById(Integer id);
    List<MedicalExaminationResponse> getAllMedicalExamination();

    MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest);

    List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id);
}
