package com.medical.app.service;

import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;

import java.util.List;

public interface MedicalExaminationDetailService {

    MedicalExaminationDetailsResponse saveMedicalExaminationDetail(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest);
    MedicalExaminationDetailsResponse getMedicalExamination(Integer id);
    List<MedicalExaminationDetailsResponse> getMedicalExaminations();
    List<MedicalExaminationDetailsResponse> getMedicalExaminationDetailByMedicalExamId(Integer id);
//    MedicalExaminationDetailsResponse modifyMedicalExamDetail(Integer id, MedicalExaminationDetailsRequest medicalExaminationDetailsRequest);
}
