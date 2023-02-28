package com.medical.app.service;

import com.medical.app.dto.response.MedicalDepartmentResponse;

import java.util.List;

public interface MedicalDepartmentService {

    List<MedicalDepartmentResponse> getAllMedicalDepartment();
}
