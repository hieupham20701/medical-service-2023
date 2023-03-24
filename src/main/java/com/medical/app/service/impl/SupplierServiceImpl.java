package com.medical.app.service.impl;

import com.medical.app.dto.request.ServiceRequest;
import com.medical.app.dto.request.SupplierRequest;
import com.medical.app.dto.response.SupplierResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.Supplier;
import com.medical.app.repository.SupplierRepository;
import com.medical.app.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse getSupplierById(Integer id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Supplier is not exists!"));

        return MapData.mapOne(supplier,SupplierResponse.class);
    }

    @Override
    public List<SupplierResponse> getSuppliers() {
        List<SupplierResponse> supplierResponses = MapData.mapList(supplierRepository.findAll(),SupplierResponse.class);
        return supplierResponses;
    }

    @Override
    public SupplierResponse saveSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = MapData.mapOne(supplierRequest, Supplier.class);
        supplier.setCreatedDate(new Date());
        return MapData.mapOne(supplierRepository.save(supplier),SupplierResponse.class);
    }
}
