package com.medical.app.service;

import com.medical.app.dto.request.MedicalDepartmentRequest;
import com.medical.app.dto.response.MedicalDepartmentResponse;
import com.medical.app.model.entity.MedicalDepartment;

import java.util.List;

public interface MedicalDepartmentService {

    List<MedicalDepartmentResponse> getAllMedicalDepartment();
    MedicalDepartmentResponse saveMedicalDepartment(MedicalDepartmentRequest medicalDepartmentRequest);
    MedicalDepartmentResponse getMedicalDepartment(Integer id);
}
