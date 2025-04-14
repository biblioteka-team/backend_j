package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.biblioteka.biblioteka_backend.dto.AuthDTO;
import ua.biblioteka.biblioteka_backend.dto.LoginDTO;
import ua.biblioteka.biblioteka_backend.dto.RegisterDTO;
import ua.biblioteka.biblioteka_backend.service.AuthService;

@Tag(name = "auth")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Registration new user")
    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @Operation(summary = "LogIn user")
    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }
}
