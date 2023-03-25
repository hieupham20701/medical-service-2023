package com.medical.app.service.impl;

import com.medical.app.dto.request.DetailMedicineRequest;
import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.DetailMedicineResponse;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;
import com.medical.app.dto.response.MedicalExaminationResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.*;
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
    private final DetailMedicineRepository detailMedicineRepository;
    private final MedicalExaminationDetailService medicalExaminationDetailService;
    private final DrugRepository drugRepository;
    @Override
    public MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest) {
        MedicalExamination medicalExamination = MapData.mapOne(medicalExaminationRequest, MedicalExamination.class);
        medicalExamination.setCreatedDate(new Date());
        medicalExamination.setDoctor(authRepository.findById(medicalExaminationRequest.getDoctorId()).orElse(null));
        medicalExamination.setReception(authRepository.findById(medicalExaminationRequest.getReceptionId()).orElseThrow(()-> new UsernameNotFoundException("Reception is not exists!")));
        if(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhoneNumber()).orElse(null) == null){
            medicalExamination.setPatient(MapData.mapOne(patientService.savePatientInfo(medicalExaminationRequest.getPatient()), Patient.class));
        }else {
            medicalExamination.setPatient(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhoneNumber()).orElseThrow(()-> new UsernameNotFoundException("Patient is not exists!")));
        }
        medicalExamination.setStatus(StatusMedicalDetail.valueOf(medicalExaminationRequest.getStatus()));
        return MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
    }

    @Override
    public MedicalExaminationResponse getMedicalExaminationById(Integer id) {
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")), MedicalExaminationResponse.class);
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),
                MedicalExaminationDetailsResponse.class));
        medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));

        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getAllMedicalExamination() {

        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findAll(),MedicalExaminationResponse.class);
       for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
           medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),MedicalExaminationDetailsResponse.class));
           medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));
       }
        return MapData.mapList(medicalExaminationRepository.findAll(),MedicalExaminationResponse.class);
    }

    @Override
    public MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examination is not exists!"));
        if(medicalExaminationRequest.getBuyMedicine() != null)
            medicalExamination.setBuyMedicine(medicalExaminationRequest.getBuyMedicine());
        if(medicalExaminationRequest.getDateRecheckUp() != null)
            medicalExamination.setDateRecheckUp(medicalExaminationRequest.getDateRecheckUp());
        if(medicalExaminationRequest.getDoctorId() != null)
            medicalExamination.setDoctor(authRepository.findById(medicalExaminationRequest.getDoctorId()).orElseThrow(()-> new UsernameNotFoundException("Doctor is not exists!")));
        if(medicalExaminationRequest.getStatus() != null)
            medicalExamination.setStatus(StatusMedicalDetail.valueOf(medicalExaminationRequest.getStatus()));
        if(medicalExaminationRequest.getDiagnose() != null)
            medicalExamination.setDiagnose(medicalExaminationRequest.getDiagnose());
        if(medicalExaminationRequest.getTotalPrice() != null)
            medicalExamination.setTotalPrice(medicalExaminationRequest.getTotalPrice());
        if(medicalExaminationRequest.getNote() != null){
            medicalExamination.setNote(medicalExaminationRequest.getNote());
        }
        if (medicalExaminationRequest.getWeight() != null)
            medicalExamination.setWeight(medicalExaminationRequest.getWeight());
        if (medicalExaminationRequest.getHeight() != null)
            medicalExamination.setHeight(medicalExaminationRequest.getHeight());
        if (medicalExaminationRequest.getHeartbeat() != null)
            medicalExamination.setHeartbeat(medicalExaminationRequest.getHeartbeat());
        if (medicalExaminationRequest.getBloodPressure() != null)
            medicalExamination.setBloodPressure(medicalExaminationRequest.getBloodPressure());
        if(medicalExaminationRequest.getTemperature() != null)
            medicalExamination.setTemperature(medicalExaminationRequest.getTemperature());
        if (medicalExaminationRequest.getPara() != null)
            medicalExamination.setPara(medicalExaminationRequest.getPara());

        List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = new ArrayList<>();
        if(medicalExaminationRequest.getMedicalExaminationDetailsRequests() != null){
            for(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest : medicalExaminationRequest.getMedicalExaminationDetailsRequests()){
                medicalExaminationDetailsRequest.setMedicalExaminationId(medicalExamination.getId());
                medicalExaminationDetailsResponses.add(medicalExaminationDetailService.saveMedicalExaminationDetail(medicalExaminationDetailsRequest));
            }
        }
        medicalExamination.setUpdatedDate(new Date());
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
        if(medicalExaminationRequest.getDetailMedicineRequests() != null){
            MedicalExaminationResponse medicalExaminationMedicineDetail = saveMedicineDetail(medicalExamination.getId(),medicalExaminationRequest.getDetailMedicineRequests());
            medicalExaminationResponse.setDetailMedicineResponses(medicalExaminationMedicineDetail.getDetailMedicineResponses());
        }
        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id) {
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findMedicalExaminationsByPatientId(id), MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class));
            medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));
        }
        return medicalExaminationResponses;
    }

    @Override
    public List<MedicalExaminationResponse> getMedicalExaminationByDateAndRoom(Date date, Integer room_id) {
        List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDateAndDoctorRoomId(date, room_id);
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminations,MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse: medicalExaminationResponses){
            List<DetailMedicineResponse> detailMedicineResponses = MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class);
            medicalExaminationResponse.setDetailMedicineResponses(detailMedicineResponses);
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class));
        }
        return medicalExaminationResponses;
    }

    @Override
    public MedicalExaminationResponse saveMedicineDetail(Integer examinationId, List<DetailMedicineRequest> detailMedicineRequests) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findById(examinationId).orElseThrow(()-> new UsernameNotFoundException("Not found"));
        List<DetailMedicineResponse> detailMedicineResponses = new ArrayList<>();
        for(DetailMedicineRequest detailMedicineRequest : detailMedicineRequests){
            DetailMedicine detailMedicine = MapData.mapOne(detailMedicineRequest, DetailMedicine.class);
            detailMedicine.setMedicalExamination(medicalExamination);
            detailMedicine.setCreatedDate(new Date());
            Drug drug = drugRepository.findById(detailMedicineRequest.getDrugId()).orElseThrow(()-> new UsernameNotFoundException("Drug not found!"));
            detailMedicine.setDrug(drug);
            detailMedicine.setTotalPrice(drug.getPrice()*detailMedicine.getQuality());
            detailMedicineResponses.add(MapData.mapOne(detailMedicineRepository.save(detailMedicine), DetailMedicineResponse.class));
        }
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExamination,MedicalExaminationResponse.class);
        medicalExaminationResponse.setDetailMedicineResponses(detailMedicineResponses);
        return medicalExaminationResponse;
    }
}
