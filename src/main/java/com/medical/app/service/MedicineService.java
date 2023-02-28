package com.medical.app.service;

import com.medical.app.dto.request.MedicineRequest;
import com.medical.app.dto.response.MedicineResponse;

import java.util.List;

public interface MedicineService {
    MedicineResponse saveMedicine(MedicineRequest medicineRequest);
    MedicineResponse getMedicine(Integer id);
    List<MedicineResponse> getMedicineByMedicalByPatientId(Integer id);
}
