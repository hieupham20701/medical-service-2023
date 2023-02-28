package com.medical.app.service.impl;

import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;
import com.medical.app.dto.response.MedicalExaminationResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.MedicalExamination;
import com.medical.app.model.entity.MedicalExaminationDetails;
import com.medical.app.model.entity.Patient;
import com.medical.app.model.enums.StatusMedicalDetail;
import com.medical.app.repository.*;
import com.medical.app.service.MedicalExaminationDetailService;
import com.medical.app.service.MedicalExaminationService;
import com.medical.app.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalExaminationServiceImpl implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;
    private final MedicalExaminationDetailRepository medicalExaminationDetailRepository;
    private final AuthRepository authRepository;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    private final MedicalExaminationDetailService medicalExaminationDetailService;
    @Override
    public MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest) {
        MedicalExamination medicalExamination = MapData.mapOne(medicalExaminationRequest, MedicalExamination.class);
        medicalExamination.setBuyMedicine(medicalExaminationRequest.getBuy_medicine());
        medicalExamination.setCreatedDate(new Date());
        medicalExamination.setDateRecheckUp(medicalExaminationRequest.getDate_recheck_up());
        medicalExamination.setDoctor(authRepository.findById(medicalExaminationRequest.getDoctor_id()).orElseThrow(()-> new UsernameNotFoundException("Doctor is not exists!")));
        medicalExamination.setReception(authRepository.findById(medicalExaminationRequest.getReception_id()).orElseThrow(()-> new UsernameNotFoundException("Reception is not exists!")));
        if(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhone_number()).orElse(null) == null){
            medicalExamination.setPatient(MapData.mapOne(patientService.savePatientInfo(medicalExaminationRequest.getPatient()), Patient.class));
        }else {
            medicalExamination.setPatient(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhone_number()).orElseThrow(()-> new UsernameNotFoundException("Patient is not exists!")));
        }
        medicalExamination.setStatus(StatusMedicalDetail.valueOf(medicalExaminationRequest.getStatus()));
        medicalExamination.setTotalPrice(medicalExaminationRequest.getTotal_price());
        return MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
    }

    @Override
    public MedicalExaminationResponse getMedicalExaminationById(Integer id) {
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")), MedicalExaminationResponse.class);
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),
                MedicalExaminationDetailsResponse.class));
        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getAllMedicalExamination() {

        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findAll(),MedicalExaminationResponse.class);
       for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
           medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),MedicalExaminationDetailsResponse.class));
       }
        return MapData.mapList(medicalExaminationRepository.findAll(),MedicalExaminationResponse.class);
    }

    @Override
    public MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examination is not exists!"));
        if(medicalExaminationRequest.getBuy_medicine() != null)
            medicalExamination.setBuyMedicine(medicalExaminationRequest.getBuy_medicine());
        if(medicalExaminationRequest.getDate_recheck_up() != null)
            medicalExamination.setDateRecheckUp(medicalExaminationRequest.getDate_recheck_up());
        if(medicalExaminationRequest.getDoctor_id() != null)
            medicalExamination.setDoctor(authRepository.findById(medicalExaminationRequest.getDoctor_id()).orElseThrow(()-> new UsernameNotFoundException("Doctor is not exists!")));
        if(medicalExaminationRequest.getStatus() != null)
            medicalExamination.setStatus(StatusMedicalDetail.valueOf(medicalExaminationRequest.getStatus()));
        if(medicalExaminationRequest.getDiagnose() != null)
            medicalExamination.setDiagnose(medicalExaminationRequest.getDiagnose());
        if(medicalExaminationRequest.getTotal_price() != null)
            medicalExamination.setTotalPrice(medicalExaminationRequest.getTotal_price());
        List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = new ArrayList<>();
        for(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest : medicalExaminationRequest.getMedicalExaminationDetailsRequests()){
            medicalExaminationDetailsRequest.setMedicalExamination_id(medicalExamination.getId());
            medicalExaminationDetailsResponses.add(medicalExaminationDetailService.saveMedicalExaminationDetail(medicalExaminationDetailsRequest));
        }
        medicalExamination.setUpdatedDate(new Date());
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id) {
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findMedicalExaminationsByPatientId(id), MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class));
        }
        return medicalExaminationResponses;
    }
}
