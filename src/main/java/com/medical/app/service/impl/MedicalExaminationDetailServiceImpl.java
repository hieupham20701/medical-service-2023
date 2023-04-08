package com.medical.app.service.impl;

import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.dto.response.MedicalExaminationDetailsResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.MedicalExaminationDetails;
import com.medical.app.model.enums.StatusMedicalDetail;
import com.medical.app.repository.*;
import com.medical.app.service.MedicalExaminationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalExaminationDetailServiceImpl implements MedicalExaminationDetailService {

    private final MedicalExaminationDetailRepository medicalExaminationDetailRepository;
    private final ServiceRepository serviceRepository;
    private final MedicalExaminationRepository medicalExaminationRepository;
    private final RoomRepository roomRepository;
    @Override
    public MedicalExaminationDetailsResponse saveMedicalExaminationDetail(MedicalExaminationDetailsRequest medicalExaminationDetailsRequest) {
        MedicalExaminationDetails medicalExaminationDetails = new MedicalExaminationDetails();
        medicalExaminationDetails.setCreatedDate(new Date());
        medicalExaminationDetails.setMedicalExamination(medicalExaminationRepository.findById(medicalExaminationDetailsRequest.getMedicalExaminationId()).orElseThrow(()-> new UsernameNotFoundException("Medical Examination not exist!")));
        medicalExaminationDetails.setService(serviceRepository.findById(medicalExaminationDetailsRequest.getServiceId()).orElseThrow(()-> new UsernameNotFoundException("Service is not exists!")));
//        medicalExaminationDetails.setRoom(roomRepository.findById(medicalExaminationDetailsRequest.getRoomId()).orElseThrow(()-> new UsernameNotFoundException("Room is not exists!")));
       medicalExaminationDetails.setStatus(StatusMedicalDetail.valueOf(medicalExaminationDetailsRequest.getStatus()));
        MedicalExaminationDetails medicalExaminationDetailsSaved = medicalExaminationDetailRepository.save(medicalExaminationDetails);
        return MapData.mapOne(medicalExaminationDetailsSaved, MedicalExaminationDetailsResponse.class);
    }

    @Override
    public MedicalExaminationDetailsResponse getMedicalExamination(Integer id) {
        return MapData.mapOne(medicalExaminationDetailRepository.findById(id).orElse(null), MedicalExaminationDetailsResponse.class);
    }

    @Override
    public List<MedicalExaminationDetailsResponse> getMedicalExaminations() {
        return MapData.mapList(medicalExaminationDetailRepository.findAll(),MedicalExaminationDetailsResponse.class);
    }

    @Override
    public Boolean deleteMedicalExaminationDetail(Integer id) {

        try {
            MedicalExaminationDetails medicalExaminationDetails = medicalExaminationDetailRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Not found"));
            medicalExaminationDetailRepository.delete(medicalExaminationDetails);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<MedicalExaminationDetailsResponse> getMedicalExaminationDetailByMedicalExamId(Integer id) {
        return MapData.mapList(medicalExaminationDetailRepository.findMedicalExaminationDetailsByMedicalExaminationId(id).orElseThrow(()-> new UsernameNotFoundException("Medical Examinations is not exists!")),MedicalExaminationDetailsResponse.class);
    }

//    @Override
//    public MedicalExaminationDetailsResponse modifyMedicalExamDetail(Integer id, MedicalExaminationDetailsRequest medicalExaminationDetailsRequest) {
//        MedicalExaminationDetails medicalExaminationDetails = medicalExaminationDetailRepository.findById(id).orElseThrow();
//        if(medicalExaminationDetailsRequest.getBuy_medicine() != null){
//            medicalExaminationDetails.setBuyMedicine(medicalExaminationDetailsRequest.getBuy_medicine());
//        }
//        if(medicalExaminationDetailsRequest.getDate_medical_examination() != null){
//            medicalExaminationDetails.setDateMedicalExamination(medicalExaminationDetailsRequest.getDate_medical_examination());
//        }
//        if(medicalExaminationDetailsRequest.getDate_recheck_up() != null){
//            medicalExaminationDetails.setDateRecheckUp(medicalExaminationDetailsRequest.getDate_recheck_up());
//        }
//        if(medicalExaminationDetailsRequest.getDiagnose() != null){
//            medicalExaminationDetails.setBuyMedicine(medicalExaminationDetailsRequest.getBuy_medicine());
//        }
//        if(medicalExaminationDetailsRequest.getTotal_price() != null){
//            medicalExaminationDetails.setTotalPrice(medicalExaminationDetailsRequest.getTotal_price());
//        }
//        if(medicalExaminationDetailsRequest.getStatus() != null){
//            medicalExaminationDetails.setStatus(StatusMedicalDetail.valueOf(medicalExaminationDetailsRequest.getStatus()));
//        }
//        medicalExaminationDetails.setUpdatedDate(new Date());
//        return MapData.mapOne(medicalExaminationDetailRepository.save(medicalExaminationDetails), MedicalExaminationDetailsResponse.class);
//    }
}
