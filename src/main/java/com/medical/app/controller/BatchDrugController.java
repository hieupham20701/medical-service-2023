package com.medical.app.controller;

import com.medical.app.dto.request.BatchDrugRequest;
import com.medical.app.service.BatchDrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/batch_drugs")
@RequiredArgsConstructor
@Slf4j
public class BatchDrugController {
    private final BatchDrugService batchDrugService;
    @PostMapping
    public ResponseEntity<?> saveBatchDrug(@RequestBody BatchDrugRequest batchDrugRequest){
        return ResponseEntity.ok().body(batchDrugService.saveBatchDrug(batchDrugRequest));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBatchDrugById(@PathVariable Integer id){
        return ResponseEntity.ok().body(batchDrugService.getBatchDrugById(id));
    }
    @GetMapping
    public ResponseEntity<?> getBatchDrug(){
        return ResponseEntity.ok().body(batchDrugService.getAllBatchDrug());
    }
}
