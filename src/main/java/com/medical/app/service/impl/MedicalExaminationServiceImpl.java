package com.medical.app.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medical.app.dto.request.DetailMedicineRequest;
import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.dto.response.*;
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
    private final ServiceRepository serviceRepository;
    private final MedicalAppointmentLetterRepository letterRepository;
    private final ImageUrlRepository imageUrlRepository;
    @Override
    public MedicalExaminationResponse saveMedicalExamination(MedicalExaminationRequest medicalExaminationRequest) throws JsonProcessingException {
        MedicalExamination medicalExamination = MapData.mapOne(medicalExaminationRequest, MedicalExamination.class);
        medicalExamination.setCreatedDate(new Date());
        if(medicalExaminationRequest.getDoctorId() != null){
            medicalExamination.setDoctor(authRepository.findById(medicalExaminationRequest.getDoctorId()).orElse(null));
        }else {
            medicalExamination.setDoctor(null);
        }
        if(medicalExaminationRequest.getLetterId() != null){
            medicalExamination.setLetter(letterRepository.findById(medicalExaminationRequest.getLetterId()).orElse(null));
        }else {
            medicalExamination.setLetter(null);
        }
        medicalExamination.setReception(authRepository.findById(medicalExaminationRequest.getReceptionId()).orElseThrow(()-> new UsernameNotFoundException("Reception is not exists!")));
        if(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhoneNumber()).orElse(null) == null){
            medicalExamination.setPatient(MapData.mapOne(patientService.savePatientInfo(medicalExaminationRequest.getPatient()), Patient.class));
        }else {
            medicalExamination.setPatient(patientRepository.getPatientByPhoneNumber(medicalExaminationRequest.getPatient().getPhoneNumber()).orElseThrow(()-> new UsernameNotFoundException("Patient is not exists!")));
        }

        medicalExamination.setStatus(StatusMedicalDetail.valueOf(medicalExaminationRequest.getStatus()));
       MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
       List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = new ArrayList<>();
       for(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest : medicalExaminationRequest.getMedicalExaminationDetailsRequests()){
           medicalExaminationDetailsRequest.setMedicalExaminationId(medicalExamination.getId());
           medicalExaminationDetailsResponses.add(MapData.mapOne(medicalExaminationDetailService.saveMedicalExaminationDetail(medicalExaminationDetailsRequest),MedicalExaminationDetailsResponse.class));
        }
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
        return medicalExaminationResponse;
    }

    @Override
    public MedicalExaminationResponse getMedicalExaminationById(Integer id) {
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")), MedicalExaminationResponse.class);
        List<MedicalExaminationDetails> medicalExaminationDetails = medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!"));
        List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses= new ArrayList<>();
        for(MedicalExaminationDetails medicalExaminationDetails1 : medicalExaminationDetails){
            MedicalExaminationDetailsResponse medicalExaminationDetailsResponse = MapData.mapOne(medicalExaminationDetails1,MedicalExaminationDetailsResponse.class);
            medicalExaminationDetailsResponse.setService(MapData.mapOne(medicalExaminationDetails1.getService(),ServiceResponse.class));
            List<String> image = new ArrayList<>();
            imageUrlRepository.findByMedicalExaminationDetailsId(medicalExaminationDetailsResponse.getId()).forEach(item -> {
                    image.add(item.getUrl());
            });
            medicalExaminationDetailsResponse.setImages(image);
            medicalExaminationDetailsResponses.add(medicalExaminationDetailsResponse);
        }
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);

        medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));

        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getAllMedicalExamination() {
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findAll(),MedicalExaminationResponse.class);
       for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
           List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),MedicalExaminationDetailsResponse.class);
           for(MedicalExaminationDetailsResponse medicalExaminationDetailsResponse : medicalExaminationDetailsResponses){
               List<String> image = new ArrayList<>();
               imageUrlRepository.findByMedicalExaminationDetailsId(medicalExaminationDetailsResponse.getId()).forEach(item -> {
                   image.add(item.getUrl());
               });
               medicalExaminationDetailsResponse.setImages(image);
           }
           medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
           medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));
       }
        return MapData.mapList(medicalExaminationResponses,MedicalExaminationResponse.class);
    }

    @Override
    public MedicalExaminationResponse updateMedicalExamination(Integer id, MedicalExaminationRequest medicalExaminationRequest) throws JsonProcessingException {
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
        if (medicalExaminationRequest.getResult() != null)
            medicalExamination.setResult(medicalExaminationRequest.getResult());
        if(medicalExaminationRequest.getClinicalSign() != null)
            medicalExamination.setClinicalSign(medicalExaminationRequest.getClinicalSign());
        if(medicalExaminationRequest.getDescription() != null)
            medicalExamination.setDescription(medicalExaminationRequest.getDescription());
        if(medicalExaminationRequest.getCodeicd() != null)
            medicalExamination.setCodeicd(medicalExaminationRequest.getCodeicd());
        List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses = new ArrayList<>();

        if(medicalExaminationRequest.getMedicalExaminationDetailsRequests() != null){
            for(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest : medicalExaminationRequest.getMedicalExaminationDetailsRequests()){
               if(medicalExaminationDetailsRequest.getType().equals("delete")) {
                   medicalExaminationDetailService.deleteMedicalExaminationDetail(id, medicalExaminationDetailsRequest.getServiceId());
               }else if(medicalExaminationDetailsRequest.getType().equals("add")){
                    medicalExaminationDetailsRequest.setMedicalExaminationId(medicalExamination.getId());
                    medicalExaminationDetailService.saveMedicalExaminationDetail(medicalExaminationDetailsRequest);
               }

            }
        }
        medicalExaminationDetailsResponses.addAll(MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),
                MedicalExaminationDetailsResponse.class));
        medicalExamination.setUpdatedDate(new Date());
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExaminationRepository.save(medicalExamination), MedicalExaminationResponse.class);
        medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
        if(medicalExaminationRequest.getDetailMedicineRequests() != null){
            for(DetailMedicineRequest detailMedicineRequest : medicalExaminationRequest.getDetailMedicineRequests()){
                if(detailMedicineRequest.getType().equals("delete")){
                    detailMedicineRepository.delete(detailMedicineRepository.findDetailMedicineByMedicalExaminationIdAndDrugId(id,detailMedicineRequest.getDrugId()));
                }else if (detailMedicineRequest.getType().equals("add")){
                    DetailMedicine detailMedicine = MapData.mapOne(detailMedicineRequest, DetailMedicine.class);
                    detailMedicine.setMedicalExamination(medicalExamination);
                    detailMedicine.setCreatedDate(new Date());
                    Drug drug = drugRepository.findById(detailMedicineRequest.getDrugId()).orElseThrow(()-> new UsernameNotFoundException("Drug not found!"));
                    detailMedicine.setDrug(drug);
                    detailMedicine.setTotalPrice(drug.getPrice()*detailMedicine.getQuantity());
                    detailMedicineRepository.save(detailMedicine);
                }
            }
        }
        List<DetailMedicineResponse> detailMedicineResponses = MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(id),DetailMedicineResponse.class);

        medicalExaminationResponse.setDetailMedicineResponses(detailMedicineResponses);
        return medicalExaminationResponse;
    }

    @Override
    public List<MedicalExaminationResponse> getHistoryMedicalExaminationPatientId(Integer id) {
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminationRepository.findMedicalExaminationsByPatientId(id), MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
            List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses =  MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class);
            if(medicalExaminationDetailsResponses != null){
                for(MedicalExaminationDetailsResponse medicalExaminationDetailsResponse : medicalExaminationDetailsResponses){
                    List<String> image = new ArrayList<>();
                    imageUrlRepository.findByMedicalExaminationDetailsId(medicalExaminationDetailsResponse.getId()).forEach(item -> {
                        image.add(item.getUrl());
                    });
                    medicalExaminationDetailsResponse.setImages(image);
                }
            }
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
            medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));
        }
        return medicalExaminationResponses;
    }

    @Override
    public List<MedicalExaminationResponse> getMedicalExaminationByDateAndRoomAndDoctor(Date date, Integer room_id, Integer doctorId) {
        List<MedicalExamination> medicalExaminations = null;
        if(room_id !=null && doctorId != null){
            medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDateAndDoctorRoomIdAndDoctorId(date,room_id,doctorId);
        }else {
            medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDate(date);
        }
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminations,MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse: medicalExaminationResponses){
            List<DetailMedicineResponse> detailMedicineResponses = MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class);
            medicalExaminationResponse.setDetailMedicineResponses(detailMedicineResponses);
            List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses= MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class);
            for(MedicalExaminationDetailsResponse medicalExaminationDetailsResponse : medicalExaminationDetailsResponses){
                List<String> image = new ArrayList<>();
                imageUrlRepository.findByMedicalExaminationDetailsId(medicalExaminationDetailsResponse.getId()).forEach(item -> {
                    image.add(item.getUrl());
                });
                medicalExaminationDetailsResponse.setImages(image);
            }
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
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
            detailMedicine.setTotalPrice(drug.getPrice()*detailMedicine.getQuantity());
            detailMedicineResponses.add(MapData.mapOne(detailMedicineRepository.save(detailMedicine), DetailMedicineResponse.class));
        }
        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicalExamination,MedicalExaminationResponse.class);
        medicalExaminationResponse.setDetailMedicineResponses(detailMedicineResponses);
        return medicalExaminationResponse;
    }

    @Override
    public List<MedicineResponse> getMedicineByDate(Date date) {
        List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDate(date);
        List<MedicineResponse> medicineResponses = new ArrayList<>();
        List<DetailMedicineResponse> detailMedicineResponses = new ArrayList<>();
        for (MedicalExamination medicalExamination: medicalExaminations){
            MedicineResponse medicineResponse = new MedicineResponse();
            detailMedicineResponses.addAll(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExamination.getId()),DetailMedicineResponse.class));
            medicineResponse.setDetailMedicineResponses(detailMedicineResponses);
            double totalPrice = detailMedicineResponses.stream().mapToDouble(detailMedicineResponse -> detailMedicineResponse.getTotalPrice() * detailMedicineResponse.getQuantity()).sum();
            medicineResponse.setTotalPrice(totalPrice);
            medicineResponse.setCreatedDate(medicalExamination.getCreatedDate());
            medicineResponse.setId(medicalExamination.getId());
            medicineResponses.add(medicineResponse);
        }

        return medicineResponses;
    }

    @Override
    public List<MedicalExaminationTable> getListMedicalExaminationTableView(Date date, Integer roomId, Integer doctorId) {
        List<MedicalExamination> medicalExaminations = null;
        if(roomId !=null && doctorId != null){
            medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDateAndDoctorRoomIdAndDoctorId(date,roomId,doctorId);
        }else {
            medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByCreatedDate(date);
        }
        List<MedicalExaminationTable> medicalExaminationTables = new ArrayList<>();


        for(MedicalExamination medicalExamination : medicalExaminations){
            MedicalExaminationTable medicalExaminationTable = new MedicalExaminationTable();
            medicalExaminationTable.setId(medicalExamination.getId());
            medicalExaminationTable.setDiagnose(medicalExamination.getDiagnose());
            medicalExaminationTable.setStatus(medicalExamination.getStatus().toString());
            medicalExaminationTable.setResult(medicalExamination.getResult());
            medicalExaminationTable.setOld(medicalExamination.getPatient().getDateOfBirth());
            medicalExaminationTable.setFullName(medicalExamination.getPatient().getFullName());
            medicalExaminationTables.add(medicalExaminationTable);
        }

        return medicalExaminationTables;
    }

    @Override
    public List<MedicalExaminationResponse> searchMedicalExaminationByKeyword(String type, String keyword) {
        switch (type){
            case "name":
                List<MedicalExamination> medicalExaminations =  medicalExaminationRepository.findMedicalExaminationsByPatientFullNameContaining(keyword);
                return mappingMedicalExaminationResponse(medicalExaminations);
            case "phone_number":
                List<MedicalExamination> medicalExamination2s =  medicalExaminationRepository.findMedicalExaminationsByPatientPhoneNumber(keyword);
                return mappingMedicalExaminationResponse(medicalExamination2s);
        }

        return null;
    }

    @Override
    public Integer getQuantityExaminationNotDone(Date date, Integer departmentId) {
        List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findMedicalExaminationsByDoctorRoomMedicalDepartmentIdAndCreatedDate(departmentId,date);

        return medicalExaminations.size();
    }

    private List<MedicalExaminationResponse> mappingMedicalExaminationResponse(List<MedicalExamination> medicalExaminations){
        List<MedicalExaminationResponse> medicalExaminationResponses = MapData.mapList(medicalExaminations, MedicalExaminationResponse.class);
        for (MedicalExaminationResponse medicalExaminationResponse : medicalExaminationResponses){
            List<MedicalExaminationDetailsResponse> medicalExaminationDetailsResponses =  MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(medicalExaminationResponse.getId()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination not found")),MedicalExaminationDetailsResponse.class);
            if(medicalExaminationDetailsResponses != null){
                for(MedicalExaminationDetailsResponse medicalExaminationDetailsResponse : medicalExaminationDetailsResponses){
                    List<String> image = new ArrayList<>();
                    imageUrlRepository.findByMedicalExaminationDetailsId(medicalExaminationDetailsResponse.getId()).forEach(item -> {
                        image.add(item.getUrl());
                    });
                    medicalExaminationDetailsResponse.setImages(image);
                }
            }
            medicalExaminationResponse.setMedicalExaminationDetailsResponses(medicalExaminationDetailsResponses);
            medicalExaminationResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicalExaminationId(medicalExaminationResponse.getId()),DetailMedicineResponse.class));
        }
        return medicalExaminationResponses;
    }
}
