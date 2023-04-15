package com.medical.app.service;

import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface MedicalExaminationDetailService {

    MedicalExaminationDetailsResponse saveMedicalExaminationDetail(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest);
    MedicalExaminationDetailsResponse getMedicalExamination(Integer id);
    List<MedicalExaminationDetailsResponse> getMedicalExaminations();
    Boolean deleteMedicalExaminationDetail(Integer id);
    List<MedicalExaminationDetailsResponse> getMedicalExaminationDetailByMedicalExamId(Integer id);
    MedicalExaminationDetailsResponse updateMedicalExaminationDetail(Integer id, String status, List<MultipartFile> image);
    List<MedicalExaminationDetailsResponse> getDetailExaminationByDateAndRoom(Date date, Integer roomId);
}
