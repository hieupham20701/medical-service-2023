package com.medical.app.controller;

import com.medical.app.dto.request.MedicineRequest;
import com.medical.app.service.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
@Slf4j
public class MedicineController {
    private final MedicineService medicineService;
    @PostMapping
    public ResponseEntity<?> saveMedicine(@RequestBody MedicineRequest medicineRequest){
        try {
            return ResponseEntity.ok().body(medicineService.saveMedicine(medicineRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMedicine(@PathVariable Integer id){
        return ResponseEntity.ok().body(medicineService.getMedicine(id));
    }
    @GetMapping(value = "/patients/{patient_id}")
    public ResponseEntity<?> getMedicinesByPatient(@PathVariable Integer patient_id) {
        return ResponseEntity.ok().body(medicineService.getMedicineByMedicalByPatientId(patient_id));
    }

}
