package com.rentatools.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentatools.infrastructure.common.exception.GlobalExceptionHandler;
import com.rentatools.infrastructure.common.security.controllers.AuthController;
import com.rentatools.infrastructure.common.security.dtos.LoginRequest;
import com.rentatools.infrastructure.common.security.dtos.RefreshTokenRequest;
import com.rentatools.infrastructure.common.security.jwt.JwtTokenService;
import com.rentatools.infrastructure.common.security.userdetails.CustomUserDetails;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Fase 9 — Prueba de Integración: Autenticación, JWT y Rotación de Tokens")
class AuthIntegrationTest {

    private MockMvc mockMvc;
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtTokenService = mock(JwtTokenService.class);

        AuthController authController = new AuthController(authenticationManager, jwtTokenService);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /api/auth/login con credenciales válidas debe retornar 200 OK con access token y refresh token")
    void shouldLoginSuccessfully() throws Exception {
        UUID userId = UUID.randomUUID();
        String email = "cliente@rentatools.com";
        String password = "Password123*";

        CustomUserDetails userDetails = new CustomUserDetails(
                userId, email, "$2a$10$hashedpassword", true, Set.of("CLIENT")
        );
        Authentication authResult = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authResult);
        when(jwtTokenService.generateAccessToken(eq(userId), eq(email), any()))
                .thenReturn("mocked.jwt.access.token");
        when(jwtTokenService.generateRefreshToken(eq(userId), eq(email)))
                .thenReturn("mocked.jwt.refresh.token");

        LoginRequest request = new LoginRequest(email, password);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.accessToken").value("mocked.jwt.access.token"))
                .andExpect(jsonPath("$.refreshToken").value("mocked.jwt.refresh.token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.roles[0]").value("CLIENT"));
    }

    @Test
    @DisplayName("POST /api/auth/login con credenciales incorrectas debe retornar 401 Unauthorized")
    void shouldReturn401OnBadCredentials() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        LoginRequest request = new LoginRequest("cliente@rentatools.com", "WrongPassword");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("No Autorizado"));
    }

    @Test
    @DisplayName("POST /api/auth/refresh con refresh token válido debe rotar y retornar nuevo access token")
    void shouldRefreshTokenSuccessfully() throws Exception {
        UUID userId = UUID.randomUUID();
        String email = "admin@rentatools.com";
        String refreshToken = "valid.refresh.token";

        Claims mockClaims = mock(Claims.class);
        when(mockClaims.get("uid", String.class)).thenReturn(userId.toString());

        when(jwtTokenService.validateToken(refreshToken)).thenReturn(true);
        when(jwtTokenService.extractUsername(refreshToken)).thenReturn(email);
        when(jwtTokenService.extractAllClaims(refreshToken)).thenReturn(mockClaims);
        when(jwtTokenService.generateAccessToken(eq(userId), eq(email), any()))
                .thenReturn("new.access.token");
        when(jwtTokenService.generateRefreshToken(eq(userId), eq(email)))
                .thenReturn("new.refresh.token");

        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);

        mockMvc.perform(post("/api/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("new.access.token"))
                .andExpect(jsonPath("$.refreshToken").value("new.refresh.token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }
}
