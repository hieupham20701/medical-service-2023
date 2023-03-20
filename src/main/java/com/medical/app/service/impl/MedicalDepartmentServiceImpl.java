package com.medical.app.service.impl;

import com.medical.app.dto.request.MedicalDepartmentRequest;
import com.medical.app.dto.response.MedicalDepartmentResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.MedicalDepartment;
import com.medical.app.repository.MedicalDepartmentRepository;
import com.medical.app.service.MedicalDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public MedicalDepartmentResponse saveMedicalDepartment(MedicalDepartmentRequest medicalDepartmentRequest) {
        MedicalDepartment medicalDepartment = MapData.mapOne(medicalDepartmentRequest,MedicalDepartment.class);
        medicalDepartment.setCreatedDate(new Date());
        return MapData.mapOne(medicalDepartmentRepository.save(medicalDepartment),MedicalDepartmentResponse.class);
    }

    @Override
    public MedicalDepartmentResponse getMedicalDepartment(Integer id) {
        return MapData.mapOne(medicalDepartmentRepository.findById(id).orElse(null), MedicalDepartmentResponse.class);
    }

}
