//package com.medical.app.service.impl;
//
//import com.medical.app.dto.request.DetailMedicineRequest;
//import com.medical.app.dto.request.MedicineRequest;
//import com.medical.app.dto.response.DetailMedicineResponse;
//import com.medical.app.dto.response.MedicalExaminationResponse;
//import com.medical.app.dto.response.MedicineResponse;
//import com.medical.app.mapper.MapData;
//import com.medical.app.model.entity.DetailMedicine;
//import com.medical.app.model.entity.Drug;
//import com.medical.app.repository.*;
//import com.medical.app.service.DetailBatchDrugService;
//import com.medical.app.service.DrugService;
//import com.medical.app.service.MedicineService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class MedicineServiceImpl implements MedicineService {
//    private final MedicalExaminationRepository medicalExaminationRepository;
//    private final DetailMedicineRepository detailMedicineRepository;
//    private final DrugRepository drugRepository;
//    private final DrugService drugService;
//
//    private final DetailBatchDrugService detailBatchDrugService;
//    @Override
//    public MedicineResponse saveMedicine(MedicineRequest medicineRequest) {
//        Medicine medicine = MapData.mapOne(medicineRequest, Medicine.class);
//        medicine.setCreatedDate(new Date());
//        medicine.setMedicalExamination(medicalExaminationRepository.findById(medicineRequest.getMedical_examination_id()).orElseThrow(() -> new UsernameNotFoundException("Medical Examination is not exist")));
//        medicine.setTotalPrice(medicineRequest.getTotal_price());
//
//        Medicine medicineSaved = medicineRepository.save(medicine);
//        MedicineResponse medicineResponse = MapData.mapOne(medicineSaved,MedicineResponse.class);
//        MedicalExaminationResponse medicalExaminationResponse = MapData.mapOne(medicineSaved.getMedicalExamination(),MedicalExaminationResponse.class);
//        medicineResponse.setMedicalExaminationResponse(medicalExaminationResponse);
//        List<DetailMedicineResponse> detailMedicineResponses = new ArrayList<>();
//        for(DetailMedicineRequest detailMedicineRequest : medicineRequest.getDetail_medicines()){
//            DetailMedicine detailMedicine = MapData.mapOne(detailMedicineRequest, DetailMedicine.class);
//            detailMedicine.setMedicine(medicineSaved);
//            detailMedicine.setCreatedDate(new Date());
//            detailMedicine.setTotalPrice(detailMedicineRequest.getTotal_price());
//            Drug drug = drugRepository.findById(detailMedicineRequest.getDrug_id()).orElseThrow(()-> new UsernameNotFoundException("Drug not found!"));
//            detailMedicine.setDrug(drug);
////            Integer totalDrugQuality = drugService.getQualityDrug(drug.getId());
////            Integer remainDrugQuality = totalDrugQuality - detailMedicineRequest.getQuality();
////            detailBatchDrugService.updateQualityDetailBatchDrug();
//
//            detailMedicineResponses.add(MapData.mapOne(detailMedicineRepository.save(detailMedicine), DetailMedicineResponse.class));
//        }
//        medicineResponse.setDetailMedicineResponses(detailMedicineResponses);
//        return medicineResponse;
//    }
//
//    @Override
//    public MedicineResponse getMedicine(Integer id) {
//        Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Medicine is not exists!"));
//        log.info(medicine.toString());
//        MedicineResponse medicineResponse = MapData.mapOne(medicine, MedicineResponse.class);
//        medicineResponse.setMedicalExaminationResponse(MapData.mapOne(medicine.getMedicalExamination(),MedicalExaminationResponse.class));
//        medicineResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicineId(id), DetailMedicineResponse.class));
//        return medicineResponse;
//    }
//
//    @Override
//    public List<MedicineResponse> getMedicineByMedicalByPatientId(Integer id) {
//        List<Medicine> medicines = medicineRepository.findMedicinesByMedicalExaminationPatientId(id);
//        List<MedicineResponse> medicineResponses = new ArrayList<>();
//        for(Medicine medicine : medicines){
//            MedicineResponse medicineResponse = MapData.mapOne(medicine,MedicineResponse.class);
//            medicineResponse.setDetailMedicineResponses(MapData.mapList(detailMedicineRepository.findDetailMedicinesByMedicineId(medicineResponse.getId()),DetailMedicineResponse.class));
//            medicineResponse.setMedicalExaminationResponse(MapData.mapOne(medicine.getMedicalExamination(),MedicalExaminationResponse.class));
//            medicineResponses.add(medicineResponse);
//        }
//        return medicineResponses;
//    }
//}
