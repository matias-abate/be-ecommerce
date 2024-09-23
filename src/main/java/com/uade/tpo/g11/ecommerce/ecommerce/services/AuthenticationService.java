package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginRequestDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginResponseDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.BadRequestException;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.UserAlreadyExistsException;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserInfoDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.controllers.auth.*;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (IUserRepository.existsByEmail(request.getEmail())) throw new UserAlreadyExistsException(request.getEmail());
        if (request.getPassword() == null) throw new BadRequestException("Password is required");

        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .message("The user was created.")
                .build();
    }
    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }

    public UserInfoDTO getUserInfo() {
        return getAuthenticatedUser().toDto();
    }
}

