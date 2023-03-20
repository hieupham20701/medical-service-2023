package com.medical.app.service.impl;

import com.medical.app.dto.request.CategoryDrugRequest;
import com.medical.app.dto.response.CategoryDrugResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.CategoryDrug;
import com.medical.app.repository.CategoryDrugRepository;
import com.medical.app.service.CategoryDrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryDrugServiceImpl implements CategoryDrugService {

    private final CategoryDrugRepository categoryDrugRepository;
    @Override
    public List<CategoryDrugResponse> getAllCategoryDrugs() {
        return MapData.mapList(categoryDrugRepository.findAll(),CategoryDrugResponse.class);
    }

    @Override
    public CategoryDrugResponse getCategoryDrugById(Integer id) {
        return MapData.mapOne(categoryDrugRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Category Drug not found")),CategoryDrugResponse.class);
    }

    @Override
    public CategoryDrugResponse saveCategoryDrug(CategoryDrugRequest categoryDrugRequest) {
        CategoryDrug categoryDrug = MapData.mapOne(categoryDrugRequest, CategoryDrug.class);
        categoryDrug.setCreatedDate(new Date());
        return MapData.mapOne(categoryDrugRepository.save(categoryDrug), CategoryDrugResponse.class);
    }
}
