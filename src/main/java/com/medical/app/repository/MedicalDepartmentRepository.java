package com.medical.app.repository;

import com.medical.app.model.entity.MedicalDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDepartmentRepository extends JpaRepository<MedicalDepartment, Integer> {

}
