package com.medical.app.service.impl;

import com.medical.app.dto.response.UserResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.User;
import com.medical.app.model.enums.UserRole;
import com.medical.app.repository.AuthRepository;
import com.medical.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthRepository authRepository;
    private final UploadServiceImpl uploadService;
    @Override
    public UserResponse changeRole(String role, Integer id) {
        User user = authRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        user.setRole(UserRole.valueOf(role));
        return MapData.mapOne(authRepository.save(user), UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {

        return MapData.mapList(authRepository.findUsersByRoleIsNot(UserRole.ADMIN), UserResponse.class);
    }

    @Override
    public List<UserResponse> getUserByRole(String role) {
        List<User> users = authRepository.findUsersByRole(UserRole.valueOf(role));
        return MapData.mapList(users,UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return null;
    }

    @Override
    public String uploadAvatar(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(uploadService.getExtension(fileName));
            File file = uploadService.convertToFile(multipartFile,fileName);

            String url = uploadService.uploadFile(file,fileName);

            file.delete();

            return url;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
