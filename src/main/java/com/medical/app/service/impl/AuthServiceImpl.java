package com.medical.app.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.app.dto.request.UserRequest;
import com.medical.app.dto.response.UserResponse;
import com.medical.app.exceptions.ForbiddenException;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.User;
import com.medical.app.model.enums.UserRole;
import com.medical.app.repository.AuthRepository;
import com.medical.app.service.AuthService;
import com.medical.app.utils.JWTTokenCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements UserDetailsService, AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = MapData.mapOne(userRequest, User.class);
        user.setRole(UserRole.valueOf(userRequest.getRole()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        User registerUser  = authRepository.save(user);
        return MapData.mapOne(registerUser, UserResponse.class);
    }

    @Override
    public UserResponse getUser(String phoneNumber) {
        return MapData.mapOne(authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User not exist!")), UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Integer userId) {
        return MapData.mapOne(authRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User is not exist")),UserResponse.class);
    }

    @Override
    public void refreshToken(HttpServletResponse response, HttpServletRequest request, Map<String, Object> body) throws IOException {
        String refreshTokenFromRequest = body.get("refreshToken").toString();

        if (refreshTokenFromRequest == null)
            throw new ForbiddenException("Can not get your refresh Token");

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshTokenFromRequest);

        Integer userId = decodedJWT.getClaim("userId").asInt();

        User existingUser = MapData.mapOne(getUserById(userId), User.class);
        UserResponse userResponse = MapData.mapOne(existingUser,UserResponse.class);

        JWTTokenCreator tokenCreator = new JWTTokenCreator(existingUser);
        String newAccessToken = tokenCreator.createToken(JWTTokenCreator.TokenType.ACCESS_TOKEN);

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("fullName", userResponse.getFullName());
        tokens.put("id",userResponse.getId());
        tokens.put("phoneNumber",userResponse.getPhoneNumber());
        tokens.put("avatar", userResponse.getAvatar());
        tokens.put("loginDate", new Date());
        tokens.put("access_token", newAccessToken);
        tokens.put("dateOfBirth", userResponse.getDateOfBirth());
        tokens.put("idCardNumber", userResponse.getIdCardNumber());
        tokens.put("address", userResponse.getAddress());
        tokens.put("room",userResponse.getRoom());
        tokens.put("createdDate", userResponse.getCreatedDate());
        tokens.put("updatedDate", userResponse.getUpdatedDate());
        tokens.put("role",userResponse.getRole());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }

    @Override
    public UserResponse changePasswordForgot(String phoneNumber) {
        User user = authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User is not exist!"));
        if(user != null){
            user.setPassword(passwordEncoder.encode(user.getPhoneNumber()));
        }
        UserResponse userResponse = MapData.mapOne(authRepository.save(user),UserResponse.class);

        return userResponse;
    }

    @Override
    public Boolean checkPhoneNumberIsExist(String phoneNumber) {
        if(authRepository.findUserByPhoneNumber(phoneNumber).orElse(null) != null){
            return true;
        }
        return false;
    }

    @Override
    public List<UserResponse> getUsersByRole(String role) {
        List<User> users = authRepository.findUsersByRole(UserRole.valueOf(role));
        return MapData.mapList(users,UserResponse.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("User is not exist"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(),user.getPassword(),authorities);
    }
}
