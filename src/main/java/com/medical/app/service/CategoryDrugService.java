package com.medical.app.service;

import com.medical.app.dto.request.CategoryDrugRequest;
import com.medical.app.dto.response.CategoryDrugResponse;
import com.medical.app.model.entity.CategoryDrug;

import java.util.List;

public interface CategoryDrugService {

    List<CategoryDrugResponse> getAllCategoryDrugs();
    CategoryDrugResponse getCategoryDrugById(Integer id);
    CategoryDrugResponse saveCategoryDrug(CategoryDrugRequest categoryDrugRequest);
}
