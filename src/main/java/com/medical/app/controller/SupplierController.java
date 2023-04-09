package com.medical.app.controller;

import com.medical.app.dto.request.SupplierRequest;
import com.medical.app.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> saveSupplier(@RequestBody SupplierRequest supplierRequest){
        try {
            return ResponseEntity.ok().body(supplierService.saveSupplier(supplierRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable Integer id){
        return ResponseEntity.ok().body(supplierService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<?> getSuppliers(){
        return ResponseEntity.ok().body(supplierService.getSuppliers());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Integer id, @RequestBody SupplierRequest supplierRequest){
        return ResponseEntity.ok().body(supplierService.updateSupplier(id, supplierRequest));
    }
}
