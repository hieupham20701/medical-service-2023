package com.medical.app.controller;

import com.google.firebase.database.annotations.NotNull;
import com.medical.app.dto.request.DetailMedicineRequest;
import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.service.BatchDrugService;
import com.medical.app.service.MedicalExaminationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/medical_examinations")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MedicalExaminationController {
    private final MedicalExaminationService medicalExaminationService;
    private final BatchDrugService batchDrugService;
    @PostMapping
    public ResponseEntity<?> saveMedicalExamination(@RequestBody MedicalExaminationRequest medicalExaminationRequest){
        try {
            return ResponseEntity.ok().body(medicalExaminationService.saveMedicalExamination(medicalExaminationRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMedicalExamination(@PathVariable Integer id){
        try {
            return ResponseEntity.ok().body(medicalExaminationService.getMedicalExaminationById(id));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllMedicalExaminations(){
        try {
            return ResponseEntity.ok().body(medicalExaminationService.getAllMedicalExamination());
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateMedicalExaminations(@PathVariable Integer id, @RequestBody MedicalExaminationRequest medicalExaminationRequest){
        try {
            return ResponseEntity.ok().body(medicalExaminationService.updateMedicalExamination(id,medicalExaminationRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping(value = "/date")
    public ResponseEntity<?> getMedicalExaminationsByDate(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date, @RequestParam(required = false) Integer roomId, @RequestParam(required = false) Integer doctorId){
        return ResponseEntity.ok().body(medicalExaminationService.getMedicalExaminationByDateAndRoomAndDoctor(date, roomId, doctorId));
    }
    @PostMapping("/medicine/{id}")
    public ResponseEntity<?> saveMedicineDetail(@PathVariable Integer id, @RequestBody List<DetailMedicineRequest> detailMedicineRequests){
        return ResponseEntity.ok().body(medicalExaminationService.saveMedicineDetail(id,detailMedicineRequests));
    }
    @PostMapping("/medicine/date")
    public ResponseEntity<?> getMedicineByDate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date){
        return ResponseEntity.ok().body(medicalExaminationService.getMedicineByDate(date));
    }

    @PostMapping(value = "/table")
    public ResponseEntity<?> getMedicalExaminationTableView(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date, @RequestParam(required = false) Integer roomId, @RequestParam(required = false) Integer doctorId){
        return ResponseEntity.ok().body(medicalExaminationService.getListMedicalExaminationTableView(date,roomId,doctorId));
    }

    @PutMapping(value = "/export/{id}")
    public ResponseEntity<?> exportDrug(@PathVariable Integer id){
        return ResponseEntity.ok(batchDrugService.exportDrug(id));
    }


}
