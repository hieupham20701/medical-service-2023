package com.medical.app.controller;

import com.medical.app.dto.request.MedicalExaminationDetailsRequest;
import com.medical.app.service.MedicalExaminationDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> updateMedicalExaminationDetail(@PathVariable Integer id, @RequestParam String status, @RequestParam List<MultipartFile> files){
        return ResponseEntity.ok().body(medicalExaminationDetailService.updateMedicalExaminationDetail(id,status,files));
    }
    @PostMapping("/service")
    public ResponseEntity<?> getMedicalMedicalExaminationDetailByDate(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date, @RequestParam Integer roomId){
        return ResponseEntity.ok().body(medicalExaminationDetailService.getDetailExaminationByDateAndRoom(date,roomId));
    }

}
