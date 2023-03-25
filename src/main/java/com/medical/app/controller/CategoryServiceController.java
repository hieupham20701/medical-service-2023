package com.medical.app.controller;

import com.medical.app.model.entity.CategoryService;
import com.medical.app.service.CategoryServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/category_services")
@RequiredArgsConstructor
public class CategoryServiceController {

    private final CategoryServiceService categoryServiceService;
    @GetMapping
    public ResponseEntity<?> getAllCategoryService(){
        return ResponseEntity.ok().body(categoryServiceService.getAllCategoryService());
    }
    @GetMapping(value = "/cls")
    public ResponseEntity<?> getCategoryServiceCLS(){
        return ResponseEntity.ok().body(categoryServiceService.getCategoryServiceCLS());
    }
}
