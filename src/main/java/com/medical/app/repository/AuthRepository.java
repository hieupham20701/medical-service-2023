package com.medical.app.repository;

import com.medical.app.model.entity.User;
import com.medical.app.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);
    List<User> findUsersByRole(UserRole role);
}
