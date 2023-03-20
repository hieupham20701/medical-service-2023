package com.medical.app.controller;


import com.medical.app.dto.request.CategoryDrugRequest;
import com.medical.app.service.CategoryDrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category_drugs")
@RequiredArgsConstructor
public class CategoryDrugController {

    private final CategoryDrugService categoryDrugService;
    @GetMapping
    public ResponseEntity<?> getAllCategoryDrug(){
        return ResponseEntity.ok().body(categoryDrugService.getAllCategoryDrugs());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryDrugService.getCategoryDrugById(id));
    }
    @PostMapping
    public ResponseEntity<?> saveCategoryDrug(@RequestBody CategoryDrugRequest categoryDrugRequest){
        try {
            return ResponseEntity.ok().body(categoryDrugService.saveCategoryDrug(categoryDrugRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
