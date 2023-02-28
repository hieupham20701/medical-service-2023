package com.medical.app.service;

import com.medical.app.dto.request.BatchDrugRequest;
import com.medical.app.dto.response.BatchDrugResponse;

import java.util.List;

public interface BatchDrugService {

    BatchDrugResponse saveBatchDrug(BatchDrugRequest  batchDrugRequest);
    BatchDrugResponse getBatchDrugById(Integer id);
    List<BatchDrugResponse> getAllBatchDrug();
}
