package com.medical.app.service;

import com.medical.app.dto.request.SupplierRequest;
import com.medical.app.dto.response.SupplierResponse;
import com.medical.app.model.entity.Supplier;

import java.util.List;

public interface SupplierService {

    SupplierResponse getSupplierById(Integer id);
    List<SupplierResponse> getSuppliers();
    SupplierResponse saveSupplier(SupplierRequest supplierRequest);

}
