package com.medical.app.service.impl;

import com.medical.app.dto.request.ServiceRequest;
import com.medical.app.dto.response.ServiceResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.Service;
import com.medical.app.repository.CategoryServiceRepository;
import com.medical.app.repository.MedicalDepartmentRepository;
import com.medical.app.repository.ServiceRepository;
import com.medical.app.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@Slf4j
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryServiceRepository categoryServiceRepository;
    private final MedicalDepartmentRepository medicalDepartmentRepository;
    @Override
    public List<ServiceResponse> getAllService() {
        return MapData.mapList(serviceRepository.findAll(), ServiceResponse.class);
    }

    @Override
    public ServiceResponse saveService(ServiceRequest serviceRequest) {
        Service service = MapData.mapOne(serviceRequest, Service.class);
        service.setCreatedDate(new Date());
        service.setCategoryService(categoryServiceRepository.findById(serviceRequest.getCategory_service_id()).orElseThrow(()-> new UsernameNotFoundException("Category Service is not found!")));
        service.setMedicalDepartment(medicalDepartmentRepository.findById(serviceRequest.getMedical_department_id()).orElseThrow(()-> new UsernameNotFoundException("Medical Department is not found!")));
        return MapData.mapOne(serviceRepository.save(service),ServiceResponse.class);
    }

    @Override
    public ServiceResponse getService(Integer id) {
        return MapData.mapOne(serviceRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Service is not exists!")),ServiceResponse.class);
    }
}
