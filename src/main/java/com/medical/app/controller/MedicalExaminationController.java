package com.medical.app.controller;

import com.medical.app.dto.request.MedicalExaminationRequest;
import com.medical.app.service.MedicalExaminationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/medical_examinations")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MedicalExaminationController {
    private final MedicalExaminationService medicalExaminationService;

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

}
