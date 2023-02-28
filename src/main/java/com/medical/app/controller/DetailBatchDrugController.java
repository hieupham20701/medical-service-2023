package com.medical.app.controller;

import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.service.DetailBatchDrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detail_batch_drugs")
@RequiredArgsConstructor
@Slf4j
public class DetailBatchDrugController {

    private final DetailBatchDrugService detailBatchDrugService;
    @PostMapping
    public ResponseEntity<?> saveDetailBatchDrug(@RequestBody DetailBatchDrugRequest detailBatchDrugRequest){
        return ResponseEntity.ok().body(detailBatchDrugService.saveDetailBatchDrug(detailBatchDrugRequest));
    }
    @GetMapping(value = "/{batch_drug_id}")
    public ResponseEntity<?> getDetailBatchDrugs(@PathVariable Integer batch_drug_id){
        return ResponseEntity.ok().body(detailBatchDrugService.getDetailByBatchDrugId(batch_drug_id));
    }
}
