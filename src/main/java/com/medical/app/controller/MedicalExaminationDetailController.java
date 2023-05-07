package com.medical.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.service.MedicalExaminationDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/medical_detail_examinations")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MedicalExaminationDetailController {

    private final MedicalExaminationDetailService medicalExaminationDetailService;
    @PostMapping
    public ResponseEntity<?> saveMedicalExamination(@RequestBody MedicalExaminationDetailsRequest medicalExaminationDetailsRequest){
        try {
            return ResponseEntity.ok().body(medicalExaminationDetailService.saveMedicalExaminationDetail(medicalExaminationDetailsRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMedicalExamination(@PathVariable Integer id){
        return ResponseEntity.ok().body(medicalExaminationDetailService.getMedicalExamination(id));
    }

    @GetMapping
    public ResponseEntity<?> getMedicalExaminations(){
        return ResponseEntity.ok().body(medicalExaminationDetailService.getMedicalExaminations());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicalExaminationDetail(@PathVariable Integer id, @RequestParam String status, @RequestParam (required = false) List<MultipartFile> files, @RequestParam(required = false) String result, @RequestParam(required = false) String conclusions) throws JsonProcessingException {
        return ResponseEntity.ok().body(medicalExaminationDetailService.updateMedicalExaminationDetail(id,status,files,result, conclusions));
    }
    @PostMapping("/service")
    public ResponseEntity<?> getMedicalMedicalExaminationDetailByDate(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date){
        return ResponseEntity.ok().body(medicalExaminationDetailService.getDetailExaminationByDate(date));
    }

    @PutMapping("/paid")
    public ResponseEntity<?> paidMedicalExaminationDetail(@RequestBody List<Integer> detailId){
        return ResponseEntity.ok().body(medicalExaminationDetailService.paidMedicalExaminationDetail(detailId));
    }
}
