package com.medical.app.service;

import com.medical.app.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserResponse changeRole(String role, Integer id);
    List<UserResponse> getAllUser();
    List<UserResponse> getUserByRole(String role);
    UserResponse getUserById(Integer id);
    String uploadAvatar(MultipartFile file);
    List<UserResponse> getUsersByDepartment(Integer departmentId, String role);
}
