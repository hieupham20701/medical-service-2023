package com.medical.app.controller;

import com.medical.app.dto.request.UserRequest;
import com.medical.app.dto.response.UserResponse;
import com.medical.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld(){
        return ResponseEntity.ok().body("Hello World");
    }
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        try{
            return ResponseEntity.created(uri).body(authService.register(userRequest));
        }catch (Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("error_message",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping(value = "/refresh_token", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> body){
        try {
            authService.refreshToken(response, request, body);
            return ResponseEntity.ok().body("New Access Token generated");
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "/forgot/password/{phone_number}")
    public ResponseEntity<?> changePasswordForgot(@PathVariable String phone_number){
        UserResponse userResponse = authService.changePasswordForgot(phone_number);
        if(userResponse != null){
            return ResponseEntity.ok().body(userResponse);
        }
        return ResponseEntity.badRequest().body("Something is wrong!");
    }

    @GetMapping(value = "/exist/{phone_number}")
    public ResponseEntity<?> checkPhoneNumberExist(@PathVariable String phone_number){
        Map<String,String> message = new HashMap<>();
        if(authService.checkPhoneNumberIsExist(phone_number)){
            message.put("message","Phone number is exists");
            return ResponseEntity.badRequest().body(message);
        }
        else {
            message.put("message","Phone number is not exists");
            return ResponseEntity.ok().body(message);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllDoctor(@RequestParam String role){
        return ResponseEntity.ok().body(authService.getUsersByRole(role));
    }
}
