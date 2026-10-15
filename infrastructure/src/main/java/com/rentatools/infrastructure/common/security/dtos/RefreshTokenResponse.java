package com.rentatools.infrastructure.common.security.dtos;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {
}
