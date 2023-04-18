package com.medical.app.controller;

import com.medical.app.dto.request.ServiceRequest;
import com.medical.app.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/services")
@CrossOrigin
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    @GetMapping
    public ResponseEntity<?> getAllService(){
        return ResponseEntity.ok().body(serviceService.getAllService());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getService(@PathVariable Integer id){
        return ResponseEntity.ok().body(serviceService.getService(id));
    }

    @PostMapping
    public ResponseEntity<?> saveService(@RequestBody ServiceRequest serviceRequest){
        try {
            return ResponseEntity.ok().body(serviceService.saveService(serviceRequest));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Integer id, @RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok().body(serviceService.updateService(id, serviceRequest));
    }

    @GetMapping("/cls")
    public ResponseEntity<?> getServiceByCls(@RequestBody Boolean cls){
        System.out.println(cls.toString());
        return ResponseEntity.ok().body(serviceService.getServiceByCls(Boolean.getBoolean(cls.toString())));
    }
}
