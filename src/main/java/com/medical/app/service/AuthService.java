package com.medical.app.service;

import com.medical.app.dto.request.UserRequest;
import com.medical.app.dto.response.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AuthService {
    UserResponse register(UserRequest userRequest);
    UserResponse getUser(String phoneNumber);
    UserResponse getUserById(Integer userId);
    void refreshToken(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> body) throws IOException;
    UserResponse changePasswordForgot(String phoneNumber);
    Boolean checkPhoneNumberIsExist(String phoneNumber);

    List<UserResponse> getUsersByRole(String role);
}
