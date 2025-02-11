package com.example.schedulerv2.controller;

import com.example.schedulerv2.dto.login.LoginRequestDto;
import com.example.schedulerv2.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.login(dto, request, response));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        authService.logout(request, response);
        return ResponseEntity.ok("로그아웃 성공");
    }
}
