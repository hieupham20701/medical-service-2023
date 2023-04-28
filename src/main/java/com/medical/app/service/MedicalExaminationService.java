package com.medical.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medical.app.dto.request.DetailMedicineRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.MedicalExaminationResponse;
import com.medical.app.dto.response.MedicalExaminationTable;
import com.medical.app.dto.response.MedicineResponse;

import java.util.Date;
import java.util.List;

public interface MedicalExaminationService {

    MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest) throws JsonProcessingException;
    MedicalExaminationResponse getMedicalExaminationById(Integer id);
    List<MedicalExaminationResponse> getAllMedicalExamination();

    MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest) throws JsonProcessingException;

    List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id);
    List<MedicalExaminationResponse> getMedicalExaminationByDateAndRoomAndDoctor(Date date, Integer room_id, Integer doctorId);
    MedicalExaminationResponse saveMedicineDetail(Integer examinationId, List<DetailMedicineRequest> detailMedicineRequests);
    List<MedicineResponse> getMedicineByDate(Date date);
    List<MedicalExaminationTable> getListMedicalExaminationTableView(Date date, Integer roomId, Integer doctorId);

}
