package com.medical.app.service.impl;

import com.medical.app.dto.request.PatientRequest;
import com.medical.app.dto.response.PatientResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.Patient;
import com.medical.app.repository.AuthRepository;
import com.medical.app.repository.PatientRepository;
import com.medical.app.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;
    private final AuthRepository authRepository;

    @Override
    public PatientResponse getPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.getPatientByPhoneNumber(phoneNumber).orElse(null);
        if (patient != null) {
            return MapData.mapOne(patient, PatientResponse.class);
        }
        return null;
    }

    @Override
    public PatientResponse savePatientInfo(PatientRequest request) {
        Patient patient = MapData.mapOne(request, Patient.class);
        patient.setCreatedDate(new Date());
        return MapData.mapOne(patientRepository.save(patient), PatientResponse.class);
    }

    @Override
    public List<PatientResponse> getAllPatient() {
        return MapData.mapList(patientRepository.findAll(), PatientResponse.class);
    }

    @Override
    public PatientResponse getPatientById(Integer id) {
        return MapData.mapOne(patientRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Patient not found!")), PatientResponse.class);
    }

    @Override
    public PatientResponse updatedPatient(Integer id, PatientRequest request) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Not found"));
        if(request.getSex() != null){
            patient.setSex(request.getSex());
        }
        if(request.getPhoneNumber()!= null){
            patient.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getAddress()!= null){
            patient.setAddress(request.getAddress());
        }
        if(request.getDateOfBirth() != null){
            patient.setDateOfBirth(request.getDateOfBirth());
        }
        if(request.getFullName() != null){
            patient.setFullName(request.getFullName());
        }
        if(request.getIdCard() != null){
            patient.setIdCard(request.getIdCard());
        }
        patient.setUpdatedDate(new Date());
        Patient patientSaved = patientRepository.save(patient);
        return MapData.mapOne(patientSaved,PatientResponse.class);
    }
}
