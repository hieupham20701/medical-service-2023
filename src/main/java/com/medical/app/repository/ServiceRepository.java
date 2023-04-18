package com.medical.app.repository;

import com.medical.app.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {

    List<Service> findServicesByCategoryServiceIsCls(Boolean cls);

}
