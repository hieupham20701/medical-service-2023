package com.medical.app.service.impl;

import com.medical.app.dto.request.MedicalAppointmentLetterRequest;
import com.medical.app.dto.response.MedicalAppointmentLetterResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.MedicalAppointmentLetter;
import com.medical.app.model.enums.StatusLetter;
import com.medical.app.repository.AuthRepository;
import com.medical.app.repository.MedicalAppointmentLetterRepository;
import com.medical.app.repository.ServiceRepository;
import com.medical.app.service.MedicalAppointLetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalAppointLetterServiceImpl implements MedicalAppointLetterService {

    private final MedicalAppointmentLetterRepository medicalAppointmentLetterRepository;
    private final AuthRepository authRepository;
    private final ServiceRepository serviceRepository;
    @Override
    public MedicalAppointmentLetterResponse saveMedicalAppointmentLetter(MedicalAppointmentLetterRequest medicalAppointmentLetterRequest) {
        MedicalAppointmentLetter medicalAppointmentLetter = MapData.mapOne(medicalAppointmentLetterRequest,MedicalAppointmentLetter.class);
        medicalAppointmentLetter.setCreatedDate(new Date(System.currentTimeMillis()));
        medicalAppointmentLetter.setPatientName(medicalAppointmentLetterRequest.getPatient_name());
        medicalAppointmentLetter.setPhoneNumber(medicalAppointmentLetterRequest.getPhone_number());
        medicalAppointmentLetter.setDoctor(authRepository.findById(medicalAppointmentLetterRequest.getDoctor_id()).orElse(null));
        medicalAppointmentLetter.setService(serviceRepository.findById(medicalAppointmentLetterRequest.getService_id()).orElseThrow(()-> new UsernameNotFoundException("Service is not found")));
        medicalAppointmentLetter.setCreator(authRepository.findById(medicalAppointmentLetterRequest.getCreator_id()).orElseThrow(()-> new UsernameNotFoundException("User not found!")));

        return MapData.mapOne(medicalAppointmentLetterRepository.save(medicalAppointmentLetter), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public MedicalAppointmentLetterResponse getLetterByPatientPhoneNumber(String phoneNumber) {
        return MapData.mapOne(medicalAppointmentLetterRepository.findMedicalAppointmentLetterByPhoneNumber(phoneNumber).orElse(null), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public MedicalAppointmentLetterResponse getLetterById(Integer id) {
        return MapData.mapOne(medicalAppointmentLetterRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Letter not found")), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> getAllLetter() {
        return MapData.mapList(medicalAppointmentLetterRepository.findAll(), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> findLettersByDateBetween(Date from, Date to) {
        return MapData.mapList(medicalAppointmentLetterRepository.findMedicalAppointmentLettersByDateBetween(from,to), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public MedicalAppointmentLetterResponse updateMedicalAppointmentLetter(Integer id, MedicalAppointmentLetterRequest medicalAppointmentLetterRequest) {

        MedicalAppointmentLetter medicalAppointmentLetter = medicalAppointmentLetterRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Appointment Letter is not exist"));
        if(medicalAppointmentLetterRequest.getAddress() != null){
            medicalAppointmentLetter.setAddress(medicalAppointmentLetterRequest.getAddress());
        }
        if(medicalAppointmentLetterRequest.getService_id() != null){
            medicalAppointmentLetter.setService(serviceRepository.findById(medicalAppointmentLetterRequest.getService_id()).orElseThrow(()-> new UsernameNotFoundException("Service is not exists!")));
        }
        if(medicalAppointmentLetterRequest.getDoctor_id() != null){
            medicalAppointmentLetter.setDoctor(authRepository.findById(medicalAppointmentLetterRequest.getDoctor_id()).orElseThrow(()-> new UsernameNotFoundException("Doctor is not exists")));
        }
        if(medicalAppointmentLetterRequest.getPatient_name() !=null){
            medicalAppointmentLetter.setPatientName(medicalAppointmentLetterRequest.getPatient_name());
        }
        if(medicalAppointmentLetterRequest.getPhone_number() != null){
            medicalAppointmentLetter.setPhoneNumber(medicalAppointmentLetterRequest.getPhone_number());
        }
        if(medicalAppointmentLetterRequest.getDate() != null){
            medicalAppointmentLetter.setDate(medicalAppointmentLetterRequest.getDate());
        }
        medicalAppointmentLetter.setUpdatedDate(new Date());
        if(medicalAppointmentLetterRequest.getStatus() != null){
            medicalAppointmentLetter.setStatus(StatusLetter.valueOf(medicalAppointmentLetterRequest.getStatus()));
        }
        if(medicalAppointmentLetterRequest.getDescription()!= null){
            medicalAppointmentLetter.setDescription(medicalAppointmentLetterRequest.getDescription());
        }
        return MapData.mapOne(medicalAppointmentLetterRepository.save(medicalAppointmentLetter), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> findLetterByParam(String patientName, String phoneNumber) {
        return MapData.mapList(medicalAppointmentLetterRepository.findMedicalAppointmentLettersByPatientNameLikeOrPhoneNumber(patientName,phoneNumber),MedicalAppointmentLetterResponse.class);
    }
}
