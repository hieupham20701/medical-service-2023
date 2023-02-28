package com.medical.app.repository;

import com.medical.app.model.entity.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryServiceRepository extends JpaRepository<CategoryService, Integer> {
}
