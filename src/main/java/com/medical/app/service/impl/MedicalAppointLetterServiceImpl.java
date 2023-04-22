package com.medical.app.service.impl;

import com.medical.app.dto.request.MedicalAppointmentLetterRequest;
import com.medical.app.dto.response.MedicalAppointmentLetterResponse;
import com.medical.app.dto.response.PatientResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.MedicalAppointmentLetter;
import com.medical.app.model.entity.Patient;
import com.medical.app.model.enums.StatusLetter;
import com.medical.app.repository.AuthRepository;
import com.medical.app.repository.MedicalAppointmentLetterRepository;
import com.medical.app.repository.PatientRepository;
import com.medical.app.repository.ServiceRepository;
import com.medical.app.service.MedicalAppointLetterService;
import com.medical.app.service.PatientService;
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
    private final PatientRepository patientRepository;
    private final ServiceRepository serviceRepository;
    private final PatientService patientService;
    @Override
    public MedicalAppointmentLetterResponse saveMedicalAppointmentLetter(MedicalAppointmentLetterRequest medicalAppointmentLetterRequest) {
        MedicalAppointmentLetter medicalAppointmentLetter = MapData.mapOne(medicalAppointmentLetterRequest,MedicalAppointmentLetter.class);
        medicalAppointmentLetter.setCreatedDate(new Date());
        if(medicalAppointmentLetterRequest.getDoctor_id() != null){
            medicalAppointmentLetter.setDoctor(authRepository.findById(medicalAppointmentLetterRequest.getDoctor_id()).orElse(null));
        }
        else {
            medicalAppointmentLetter.setDoctor(null);
        }
        medicalAppointmentLetter.setService(serviceRepository.findById(medicalAppointmentLetterRequest.getService_id()).orElseThrow(()-> new UsernameNotFoundException("Service is not found")));
        medicalAppointmentLetter.setCreator(authRepository.findById(medicalAppointmentLetterRequest.getCreator_id()).orElse(null));
        MedicalAppointmentLetter medicalAppointmentLetterSaved = medicalAppointmentLetterRepository.save(medicalAppointmentLetter);
        MedicalAppointmentLetterResponse medicalAppointmentLetterResponse = MapData.mapOne(medicalAppointmentLetterSaved,MedicalAppointmentLetterResponse.class);
        return medicalAppointmentLetterResponse;
    }

    @Override
    public MedicalAppointmentLetterResponse getLetterByPatientPhoneNumber(String phoneNumber) {
        return MapData.mapOne(medicalAppointmentLetterRepository.findMedicalAppointmentLetterByPatientPhoneNumber(phoneNumber).orElse(null), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public MedicalAppointmentLetterResponse getLetterById(Integer id) {
        return MapData.mapOne(medicalAppointmentLetterRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Letter not found")), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> getAllLetter() {
        List<MedicalAppointmentLetter> medicalAppointmentLetters = medicalAppointmentLetterRepository.findAll();
        List<MedicalAppointmentLetterResponse> medicalAppointmentLetterResponses = MapData.mapList(medicalAppointmentLetters, MedicalAppointmentLetterResponse.class);
        return medicalAppointmentLetterResponses;
    }

    @Override
    public List<MedicalAppointmentLetterResponse> findLettersByDateBetween(Date from, Date to) {
        return MapData.mapList(medicalAppointmentLetterRepository.findMedicalAppointmentLettersByDateBetween(from,to), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public MedicalAppointmentLetterResponse updateMedicalAppointmentLetter(Integer id, MedicalAppointmentLetterRequest medicalAppointmentLetterRequest) {

        MedicalAppointmentLetter medicalAppointmentLetter = medicalAppointmentLetterRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Appointment Letter is not exist"));

        if(medicalAppointmentLetterRequest.getService_id() != null){
            medicalAppointmentLetter.setService(serviceRepository.findById(medicalAppointmentLetterRequest.getService_id()).orElseThrow(()-> new UsernameNotFoundException("Service is not exists!")));
        }
        if(medicalAppointmentLetterRequest.getDoctor_id() != null){
            medicalAppointmentLetter.setDoctor(authRepository.findById(medicalAppointmentLetterRequest.getDoctor_id()).orElseThrow(()-> new UsernameNotFoundException("Doctor is not exists")));
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
        if(medicalAppointmentLetterRequest.getAddress() != null){
            medicalAppointmentLetter.setAddress(medicalAppointmentLetterRequest.getAddress());
        }
        if(medicalAppointmentLetterRequest.getPatientName()!= null){
            medicalAppointmentLetter.setPatientName(medicalAppointmentLetterRequest.getPatientName());
        }
        if(medicalAppointmentLetterRequest.getSex()!= null){
            medicalAppointmentLetter.setSex(medicalAppointmentLetterRequest.getSex());
        }
        if(medicalAppointmentLetterRequest.getPhoneNumber() != null){
            medicalAppointmentLetter.setPhoneNumber(medicalAppointmentLetterRequest.getPhoneNumber());
        }
        if(medicalAppointmentLetterRequest.getEmail() != null){
            medicalAppointmentLetter.setEmail(medicalAppointmentLetterRequest.getEmail());
        }
        return MapData.mapOne(medicalAppointmentLetterRepository.save(medicalAppointmentLetter), MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> findLetterByParam(String patientName, String phoneNumber) {
        return MapData.mapList(medicalAppointmentLetterRepository.findMedicalAppointmentLetterByPatientFullNameOrPatientPhoneNumber(patientName,phoneNumber),MedicalAppointmentLetterResponse.class);
    }

    @Override
    public List<MedicalAppointmentLetterResponse> findLetterByDate(Date date) {
        List<MedicalAppointmentLetter> medicalAppointmentLetters = medicalAppointmentLetterRepository.findMedicalAppointmentLetterByDate(date);
        List<MedicalAppointmentLetterResponse> medicalAppointmentLetterResponses = MapData.mapList(medicalAppointmentLetters,MedicalAppointmentLetterResponse.class);

        return medicalAppointmentLetterResponses;
    }


    private PatientResponse mapPatient(Patient patient){
       return MapData.mapOne(patient, PatientResponse.class);
    }
}
