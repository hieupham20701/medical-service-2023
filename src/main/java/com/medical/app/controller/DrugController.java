package com.medical.app.controller;

import com.medical.app.dto.request.DrugRequest;
import com.medical.app.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public ResponseEntity<?> saveDrug(@RequestBody DrugRequest drugRequest){
        return ResponseEntity.ok().body(drugService.saveDrug(drugRequest));
    }
    @GetMapping
    public ResponseEntity<?> getAllDrug(){
        return ResponseEntity.ok().body(drugService.getAllDrugs());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDrug(@PathVariable Integer id){
        return ResponseEntity.ok().body(drugService.getDrugById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDrug(@PathVariable Integer id, @RequestBody DrugRequest drugRequest){
        return ResponseEntity.ok().body(drugService.updateDrugResponse(id,drugRequest));
    }
    @PostMapping("/image")
    public ResponseEntity<?>uploadImageDrug(@RequestParam MultipartFile file){
        return ResponseEntity.ok().body(drugService.uploadImgDrug(file));
    }
}
