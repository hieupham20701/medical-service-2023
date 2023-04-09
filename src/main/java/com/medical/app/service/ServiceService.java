package com.medical.app.service;

import com.medical.app.dto.request.ServiceRequest;
import com.medical.app.dto.response.ServiceResponse;

import java.util.List;

public interface ServiceService {

    List<ServiceResponse> getAllService();
    ServiceResponse saveService(ServiceRequest serviceRequest);

    ServiceResponse getService(Integer id);
    ServiceResponse updateService(Integer id, ServiceRequest serviceRequest);

}
