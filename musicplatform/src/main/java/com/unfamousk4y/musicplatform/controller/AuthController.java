package com.unfamousk4y.musicplatform.controller;

import com.unfamousk4y.musicplatform.dto.AuthResponse;
import com.unfamousk4y.musicplatform.dto.LoginRequest;
import com.unfamousk4y.musicplatform.dto.RegisterRequest;
import com.unfamousk4y.musicplatform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
