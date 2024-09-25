package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginRequestDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.LoginResponseDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.BadRequestException;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.InvalidCredentialsException;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.UserAlreadyExistsException;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserInfoDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.UserNotFoundException;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.controllers.auth.*;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
        // Validar que el email no esté vacío
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new BadRequestException("Email is required");
        }

        // Validar que el password no esté vacío
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new BadRequestException("Password is required");
        }

        // Validar si el email ya existe en la base de datos
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email ya está en uso: " + request.getEmail());
        }

        // Crear el usuario con los datos proporcionados y hashear la contraseña
        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        // Guardar el usuario en la base de datos
        repository.save(user);

        // Generar el token JWT para el nuevo usuario
        var jwtToken = jwtService.generateToken(user);

        // Retornar la respuesta de autenticación con el token generado
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .message("El usuario fue creado exitosamente.")
                .build();
    }
    public LoginResponseDTO login(LoginRequestDTO request) {
        try {
            // Intentar autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Obtener el usuario desde el repositorio
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar el token JWT
            String jwtToken = jwtService.generateToken(user);
            return LoginResponseDTO.builder()
                    .token(jwtToken)
                    .expiresIn(jwtService.getExpirationTime())
                    .build();
        } catch (Exception e) {
            // Lanza RuntimeException con mensaje personalizado para capturarlo en el controlador
            throw new RuntimeException("Email o contraseña incorrectos");
        }
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

