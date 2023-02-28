package com.medical.app.service;

import com.medical.app.dto.request.DetailBatchDrugRequest;
import com.medical.app.dto.response.DetailBatchDrugResponse;

import java.util.List;

public interface DetailBatchDrugService {

    DetailBatchDrugResponse saveDetailBatchDrug(DetailBatchDrugRequest detailBatchDrugRequest);
    List<DetailBatchDrugResponse> getDetailByBatchDrugId(Integer id);

    DetailBatchDrugResponse updateQualityDetailBatchDrug(Integer id, Integer quality);
}
