package com.medical.app.service;

import com.medical.app.dto.request.DetailMedicineRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.MedicalExaminationResponse;
import com.medical.app.dto.response.MedicineResponse;

import java.util.Date;
import java.util.List;

public interface MedicalExaminationService {

    MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest);
    MedicalExaminationResponse getMedicalExaminationById(Integer id);
    List<MedicalExaminationResponse> getAllMedicalExamination();

    MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest);

    List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id);
    List<MedicalExaminationResponse> getMedicalExaminationByDateAndRoomAndDoctor(Date date, Integer room_id, Integer doctorId);
    MedicalExaminationResponse saveMedicineDetail(Integer examinationId, List<DetailMedicineRequest> detailMedicineRequests);
    List<MedicineResponse> getMedicineByDate(Date date);
}
