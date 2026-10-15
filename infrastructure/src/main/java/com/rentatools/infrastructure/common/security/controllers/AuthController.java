package com.rentatools.infrastructure.common.security.controllers;

import com.rentatools.infrastructure.common.security.dtos.LoginRequest;
import com.rentatools.infrastructure.common.security.dtos.LoginResponse;
import com.rentatools.infrastructure.common.security.dtos.RefreshTokenRequest;
import com.rentatools.infrastructure.common.security.dtos.RefreshTokenResponse;
import com.rentatools.infrastructure.common.security.jwt.JwtTokenService;
import com.rentatools.infrastructure.common.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replace("ROLE_", ""))
                .toList();

        String accessToken = jwtTokenService.generateAccessToken(user.getId(), user.getUsername(), roles);
        String refreshToken = jwtTokenService.generateRefreshToken(user.getId(), user.getUsername());

        return ResponseEntity.ok(new LoginResponse(
                user.getId(),
                user.getUsername(),
                roles,
                accessToken,
                refreshToken,
                "Bearer"
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        if (!jwtTokenService.validateToken(request.refreshToken())) {
            throw new BadCredentialsException("Token de actualización inválido o expirado");
        }

        String username = jwtTokenService.extractUsername(request.refreshToken());
        var claims = jwtTokenService.extractAllClaims(request.refreshToken());
        String uidStr = claims.get("uid", String.class);
        java.util.UUID userId = java.util.UUID.fromString(uidStr);

        String newAccessToken = jwtTokenService.generateAccessToken(userId, username, List.of("CLIENT"));
        String newRefreshToken = jwtTokenService.generateRefreshToken(userId, username);

        return ResponseEntity.ok(new RefreshTokenResponse(newAccessToken, newRefreshToken, "Bearer"));
    }
}
