package com.medical.app.controller;

import com.medical.app.dto.request.MedicalDepartmentRequest;
import com.medical.app.model.entity.MedicalDepartment;
import com.medical.app.service.MedicalDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/api/medical_departments")
@RequiredArgsConstructor
public class MedicalDepartmentController {

    private final MedicalDepartmentService medicalDepartmentService;
    @GetMapping
    public ResponseEntity<?> getAllMedicalDepartment(){
        return ResponseEntity.ok().body(medicalDepartmentService.getAllMedicalDepartment());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMedicalDepartmentById(@PathVariable Integer id){
        return ResponseEntity.ok().body(medicalDepartmentService.getMedicalDepartment(id));
    }

    @PostMapping
    public ResponseEntity<?> saveMedicalDepartment(@RequestBody MedicalDepartmentRequest medicalDepartmentRequest){
        return ResponseEntity.ok().body(medicalDepartmentService.saveMedicalDepartment(medicalDepartmentRequest));
    }

}
