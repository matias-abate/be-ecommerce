package com.uade.tpo.g11.ecommerce.ecommerce.controllers.auth;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginRequestDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginResponseDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserInfoDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.service = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginRequestDTO loginRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.login(loginRequestDTO));
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> userInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getUserInfo());
    }

}