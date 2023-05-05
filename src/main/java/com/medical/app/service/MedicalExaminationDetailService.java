package com.medical.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.response.DetailServiceResponse;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MedicalExaminationDetailService {

    MedicalExaminationDetailsResponse saveMedicalExaminationDetail(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest) throws JsonProcessingException;
    MedicalExaminationDetailsResponse getMedicalExamination(Integer id);
    List<MedicalExaminationDetailsResponse> getMedicalExaminations();
    Boolean deleteMedicalExaminationDetail(Integer medicalExaminationId, Integer serviceId);
    List<MedicalExaminationDetailsResponse> getMedicalExaminationDetailByMedicalExamId(Integer id);
    MedicalExaminationDetailsResponse updateMedicalExaminationDetail(Integer id, String status, List<MultipartFile> image, String result) throws JsonProcessingException;
    List<DetailServiceResponse> getDetailExaminationByDate(Date date);
    List<MedicalExaminationDetailsResponse> paidMedicalExaminationDetail(List<Integer> detailId);
}
