package com.medical.app.repository;

import com.medical.app.model.entity.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryServiceRepository extends JpaRepository<CategoryService, Integer> {
    List<CategoryService> findCategoryServicesByIsCls(Boolean is);
}
