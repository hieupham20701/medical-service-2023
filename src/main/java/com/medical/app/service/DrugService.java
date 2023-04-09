package com.medical.app.service;

import com.medical.app.dto.request.DrugRequest;
import com.medical.app.dto.response.DrugResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DrugService {

    DrugResponse saveDrug(DrugRequest drugRequest);
    List<DrugResponse> getAllDrugs();

    DrugResponse getDrugById(Integer id);

    Integer getQualityDrug(Integer id);
    DrugResponse updateDrugResponse(Integer id, DrugRequest drugRequest);
    String uploadImgDrug(MultipartFile file);
}
