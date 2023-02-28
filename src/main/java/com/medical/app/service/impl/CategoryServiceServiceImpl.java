package com.medical.app.service.impl;

import com.medical.app.dto.response.CategoryServiceResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.repository.CategoryServiceRepository;
import com.medical.app.service.CategoryServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceServiceImpl implements CategoryServiceService {

    private final CategoryServiceRepository categoryServiceRepository;

    @Override
    public List<CategoryServiceResponse> getAllCategoryService() {
        return MapData.mapList(categoryServiceRepository.findAll(),CategoryServiceResponse.class);
    }
}
