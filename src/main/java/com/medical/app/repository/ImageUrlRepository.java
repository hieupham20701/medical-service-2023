package com.medical.app.repository;

import com.medical.app.model.entity.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageUrlRepository extends JpaRepository<ImageUrl, Integer> {
    List<ImageUrl> findByMedicalExaminationDetailsId(Integer id);
}
