package com.uade.tpo.g11.ecommerce.ecommerce.controllers.auth;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginRequestDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginResponseDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserInfoDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.ErrorMessage;
import com.uade.tpo.g11.ecommerce.ecommerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            LoginResponseDTO response = authenticationService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Manejar la excepción RuntimeException y devolver un 403 Forbidden
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorMessage(e.getMessage(), HttpStatus.FORBIDDEN.value()));
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> userInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getUserInfo());
    }

}