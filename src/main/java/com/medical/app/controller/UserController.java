package com.medical.app.controller;

import com.medical.app.service.AuthService;
import com.medical.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    @GetMapping("/roles")
    public ResponseEntity<?> getUserByRole(@RequestParam String role){
        return ResponseEntity.ok().body(userService.getUserByRole(role));
    }
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }
    @PutMapping("/roles/{id}")
    public ResponseEntity<?> changeRole(@RequestParam String role, @PathVariable Integer id){
        return ResponseEntity.ok().body(userService.changeRole(role, id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok().body(authService.getUserById(id));
    }

}
