package com.medical.app.controller;

import com.medical.app.dto.request.MedicalAppointmentLetterRequest;
import com.medical.app.service.MedicalAppointLetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/medical_letters")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MedicalAppointmentLetterController {
    private final MedicalAppointLetterService medicalAppointLetterService;
    @PostMapping
    public ResponseEntity<?> saveLetter(@RequestBody MedicalAppointmentLetterRequest medicalAppointmentLetterRequest){
        try {
            return ResponseEntity.ok().body(medicalAppointLetterService.saveMedicalAppointmentLetter(medicalAppointmentLetterRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping(value = "/detail")
    public ResponseEntity<?> getLetterByPhoneNumber(@RequestParam String phone_number){
        try {
            return ResponseEntity.ok().body(medicalAppointLetterService.getLetterByPatientPhoneNumber(phone_number));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getLetterById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok().body(medicalAppointLetterService.getLetterById(id));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllByLetter(){
        return ResponseEntity.ok().body(medicalAppointLetterService.getAllLetter());
    }

    @PostMapping("/date")
    public ResponseEntity<?> getLettersFromDateToDate(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date from, @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date to){
        return ResponseEntity.ok().body(medicalAppointLetterService.findLettersByDateBetween(from,to));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateLetter(@PathVariable Integer id, @RequestBody MedicalAppointmentLetterRequest medicalAppointmentLetterRequest){
        try {
            return ResponseEntity.ok().body(medicalAppointLetterService.updateMedicalAppointmentLetter(id,medicalAppointmentLetterRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> findLetterByParam(@RequestParam String patient_name, @RequestParam String phone_number){
            return ResponseEntity.ok().body(medicalAppointLetterService.findLetterByParam(patient_name,phone_number));
    }
//    @PostMapping(value = "/date")
//    public ResponseEntity<?> getLetterByDate(@RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date date){
//        return ResponseEntity.ok().body(medicalAppointLetterService.findLetterByDate(date));
//    }
 }
