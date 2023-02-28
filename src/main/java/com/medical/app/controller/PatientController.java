package com.medical.app.controller;

import com.medical.app.dto.request.PatientRequest;
import com.medical.app.dto.response.PatientResponse;
import com.medical.app.service.MedicalExaminationService;
import com.medical.app.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final MedicalExaminationService medicalExaminationService;
    @GetMapping(value = "/{phone_number}")
    public ResponseEntity<?> getPatientByPhoneNumber(@PathVariable String phone_number){
        PatientResponse patientResponse = patientService.getPatientByPhoneNumber(phone_number);
        if(patientResponse != null){
            return ResponseEntity.ok().body(patientResponse);
        }else {
            Map<String, String> errors = new HashMap<>();
            errors.put("message","Not found Patient");
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> savePatient(@RequestBody PatientRequest patientRequest){
        PatientResponse patientResponse = patientService.savePatientInfo(patientRequest);
        return ResponseEntity.ok().body(patientResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatient());
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<?> findHistoryMedicalExamination(@PathVariable Integer id){
        return ResponseEntity.ok().body(medicalExaminationService.getHistoryMedicalExaminationPatientId(id));
    }

}
