package com.medical.app.service.impl;

import com.medical.app.dto.response.MedicalDepartmentResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.repository.MedicalDepartmentRepository;
import com.medical.app.service.MedicalDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class MedicalDepartmentServiceImpl implements MedicalDepartmentService {

    private final MedicalDepartmentRepository medicalDepartmentRepository;
    @Override
    public List<MedicalDepartmentResponse> getAllMedicalDepartment() {
        return MapData.mapList(medicalDepartmentRepository.findAll(),MedicalDepartmentResponse.class);
    }
}
